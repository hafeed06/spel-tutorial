package co.hafid.speltutorial.properties;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnableConfigurationProperties(NewDealerNetworkPropertiesTest.class)
class NewDealerNetworkPropertiesTest {

    @Autowired
    NewDealerNetworkProperties newDealerNetworkProperties;

    @Test
    void start() {

    }

    @Test
    void should_get_map_of_all_prefilters() {
        // Given
        Map<String, Map<String, String>> requestEnrichers = newDealerNetworkProperties.getRequestEnrichers().get("FR");
        // When

        Map<String, String> preToPostFiltersMap= new HashMap<>();
        requestEnrichers.forEach((productType, brandToFilterMap) -> brandToFilterMap.forEach(
                (brandCode, filter) -> preToPostFiltersMap.put(filter, buildPostFilter(productType, brandCode))
        ));
        List<String> preFilters = preToPostFiltersMap.values().stream().toList();

    }


    private String buildPostFilter(String productType, String brandCode) {
            return "(productType==" + productType + ";" + "brand.code==" + brandCode + ")";
    }

}
