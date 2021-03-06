import bcrypt from "bcrypt";
import jwt from "jsonwebtoken";

import UserRepository from "../repository/UserRepository.js";
import * as httpStatus from "../../../config/constants/httpStatus.js";
import UserException from "../exception/UserException.js";
import * as secrets from "../../../config/constants/secrets.js";

class UserService {

    async findByEmail(req) {
        try {
            const { email } = req.params;
            const { authUser } = req;
            this.validateRequest(email);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user);
            this.validateAuthenticatedUser(user, authUser);
            return {
                status: httpStatus.SUCCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email
                }
            }
        } catch(err) {
            console.log(err);
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.message
            }
        }
    }

    async getAccessToken(req) {
        try {
            const { email, password } = req.body;
            this.validateAccessTokenData(email, password);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user);
            await this.validatePassword(password, user.password);
            const authUser = {id: user.id, email, name: user.name};
            const accessToken = jwt.sign({authUser}, secrets.API_SECRET,{expiresIn: "1d" });
            return {
                status: httpStatus.SUCCESS,
                accessToken
            }
        } catch(err) {
            console.log(err);
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.message
            }
        }
    }

    validateAuthenticatedUser = function (user, authUser) {
        if(!authUser || (user.id !== authUser.id) ) {
            throw new UserException(
                httpStatus.FORBIDDEN,
                "You can't access this data."
            );
        }
    }

    validatePassword = async function (password, hashPassword) {
        if(!await bcrypt.compare(password, hashPassword)){
            throw new UserException(httpStatus.UNAUTHORIZED, "Password is incorrect.")
        }
    }

    validateAccessTokenData = function (email, password) {
        if(!email || !password) {
            throw new UserException(httpStatus.UNAUTHORIZED, "Email and password must bem informed");
        }
    }

    validateRequest = function (email) {
        if(!email){
            throw new UserException(
                httpStatus.BAD_REQUEST,
                'User email was not informed'
            );
        }
    }

    validateUserNotFound = function (user){
        if(!user) {
            throw new UserException(
                httpStatus.BAD_REQUEST,
                'User was not found at all'
            );
        }
    }

}

export default new UserService();