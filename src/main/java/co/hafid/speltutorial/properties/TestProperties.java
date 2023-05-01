package co.hafid.speltutorial.properties;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

/**
 * @author abelb
 */

@ConfigurationProperties(prefix = "rces.dealer-network")
@Getter
@Setter
public class TestProperties {
    private Map<String, Map<String, ProductAttributes>> requestEnrichers;

    @Getter
    @Setter
    public static class ProductAttributes {
        private String productType;
        private String brandCode;

        public String buildFilter() {
            return "productType==" + productType + ";" + "brand.code==" + brandCode;
        }
    }

    @Override
    public String toString() {
        return "TestProperties{" +
                "users=" + requestEnrichers +
                '}';
    }
}
