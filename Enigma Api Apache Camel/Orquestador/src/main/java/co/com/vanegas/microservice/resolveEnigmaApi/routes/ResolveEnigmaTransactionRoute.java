package co.com.vanegas.microservice.resolveEnigmaApi.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyRequest;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseErrors;
import co.com.vanegas.microservice.resolveEnigmaApi.model.JsonApiBodyResponseSuccess;
import co.com.vanegas.microservice.resolveEnigmaApi.strategy.JoinReplyAggregationStrategy;

@Component
public class ResolveEnigmaTransactionRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        JoinReplyAggregationStrategy myAggregationStrategy = new JoinReplyAggregationStrategy();

        from("direct:resolve-enigma")
            .routeId("resolveEnigma")
            .log("Request body ${body}")
            .process(new Processor() {
                @Override
                public void process(Exchange exchange) throws Exception {
                    JsonApiBodyRequest serviceRequestBody = (JsonApiBodyRequest) exchange.getIn().getBody();
                    exchange.setProperty("ServiceId", serviceRequestBody.getData().get(0).getHeader().getId());
                    exchange.setProperty("ServiceType", serviceRequestBody.getData().get(0).getHeader().getType());
                    exchange.setProperty("ServiceEnigma", serviceRequestBody.getData().get(0).getEnigma());
                    exchange.setProperty("Error", "0000");
                    exchange.setProperty("descError", "No error");
                }
            })
            //.to("direct:get-step-one")
            //.to("direct:get-step-two")
            //.to("direct:get-step-three")
            
    		.multicast(myAggregationStrategy)
            .parallelProcessing()
            .to("direct:get-step-one", "direct:get-step-two", "direct:get-step-three")
            .end()
            .setBody(simple("{\n" +
                    "   \"data\": [\n" +
                    "      {\n" +
                    "         \"header\": {\n" +
                    "            \"id\": \"${exchangeProperty.ServiceId}\",\n" +
                    "            \"type\": \"${exchangeProperty.ServiceType}\"\n" +
                    "         },\n" +
                    "         \"answer\": \"${exchangeProperty.Step1} - ${exchangeProperty.Step2} - ${exchangeProperty.Step3}\"\n" +
                    "      }\n" +
                    "   ]\n" +
                    "}"))
                .end();
           // ..to("freemarker:templates/ResolveEnigmaTransactionResponse.ftl"); 
    }
}
