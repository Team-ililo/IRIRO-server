
package team6.car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

        private static final String API_NAME = "IRIRO";
        private static final String API_VERSION = "0.0.1";
        private static final String API_DESCRIPTION = "IRIRO API 명세서";

        @Bean
        public Docket api() {

            Parameter parameterBuilder = new ParameterBuilder()
                    .name("")
                    .description("")
                    .modelRef(new ModelRef(""))
                    .parameterType("")
                    .required(false)
                    .build();

            List<Parameter> globalParamters = new ArrayList<>();
            globalParamters.add(parameterBuilder);

            return new Docket(DocumentationType.SWAGGER_2)
                    .globalOperationParameters(globalParamters)
                    .useDefaultResponseMessages(false)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("team6.car"))
                    .paths(PathSelectors.any())
                    .build();
        }

        public ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title(API_NAME)
                    .version(API_VERSION)
                    .description(API_DESCRIPTION)
                    .build();
        }
}
