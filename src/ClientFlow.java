import java.util.ArrayList;
import java.util.List;

/**
 * @author huzipeng
 * @version 1.0
 */

public class ClientFlow {
    public String ms;//业务描述

    public String filePath;//物理路径
    public String fileApiPath;//api物理路径
    public String fileDtoPath;//dto物理路径

    public String xName;//小写业务名称
    public String dName;//大写业务名称

    public String path;//定位路径
    public String apiPath;//api路径
    public String dtoPath;//dto路径

    List<String> classList = new ArrayList<>();
}
