import java.util.ArrayList;
import java.util.List;

/**
 * @author huzipeng
 * @version 1.0
 */

public class ServerFlow {
    public String ms;//业务描述

    public String filePath;//物理路径 例如/**/order
    public String fileApiPath;//api物理路径
    public String fileDomainPath;//domain路径
    public String fileMapperPath;//mapper路径
    public String fileServicePath;//service路径
    public String fileServiceImplPath;//serviceImpl路径

    public String xName;//小写业务名称
    public String dName;//大写业务名称

    public String path;//定位路径
    public String apiPath;//api路径
    public String domainPath;//domain路径
    public String mapperPath;//mapper路径
    public String servicePath;//service路径
    public String serviceImplPath;//serviceImpl路径
    List<String> classList = new ArrayList<>();
}
