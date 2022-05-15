package br.com.cursoudemy.productapi.modules.product.messaging;

import br.com.cursoudemy.productapi.modules.product.dto.ProductStockDTO;
import br.com.cursoudemy.productapi.modules.product.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class ProductStockListener {

    private final ProductService productService;

    @RabbitListener(queues = "${app-config.rabbit.queue.product-stock}")
    public void listeningProductStockMessages(ProductStockDTO productStockDTO) throws JsonProcessingException {
        log.info(String.format("Message received %s", new ObjectMapper().writeValueAsString(productStockDTO)));
        productService.updateProductStock(productStockDTO);
    }
}
