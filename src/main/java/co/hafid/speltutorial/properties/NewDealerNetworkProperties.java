package co.hafid.speltutorial.properties;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author abelb
 */

@ConfigurationProperties(prefix = "rces.new-dealer-network")
@Getter
@Setter
public class NewDealerNetworkProperties {
    private Map<String, Map<String, Map<String, String>>> requestEnrichers;

    @Override
    public String toString() {
        return "NewDealerNetworkProperties{" +
                "requestEnrichers=" + requestEnrichers +
                '}';
    }
}
