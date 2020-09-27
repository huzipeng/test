import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author huzipeng
 * @version 1.0
 */
public class BeanCreate {

    static String biao = "\n" +
            "/**  主键id*/  private  Integer  companyScoreCoefficientId;\n" +
            "/**  创新发展系数*/  private  BigDecimal  innovateGrowCoefficient;\n" +
            "/**  行业资质系数*/  private  BigDecimal  industryQualificationCoefficient;\n" +
            "/**  经营状况系数*/  private  BigDecimal  operatePerformanceCoefficient;\n" +
            "/**  人才结构系数*/  private  BigDecimal  talentStructureCoefficient;\n" +
            "/**  组织背景系数*/  private  BigDecimal  researchInvestCoefficient;\n" +
            "/**  总分系数*/  private  BigDecimal  corpAssessScore;\n" +
            "/**  0:五维评测*/  private  Integer  scoreType;\n" +
            "/**  是否删除*/  private  Integer  isDeleted;\n" +
            "/**  创建人*/  private  Integer  creator;\n" +
            "/**  创建人名称*/  private  String  creatorName;\n" +
            "/**  创建时间*/  private  LocalDateTime  createTime;\n" +
            "/**  更新时间*/  private  LocalDateTime  updateTime;\n";

    static String idType = "Integer";
    static String ms = "企业评分";
    static String yewu = "CompanyScoreCoefficient";
    static String clientPath = "/Users/huzipeng/Desktop/tojoy_pro/policy/policy-micro-company/micro-company-client/src/main/java/com/policy/company";
    static String serverPath = "/Users/huzipeng/Desktop/tojoy_pro/policy/policy-micro-company/micro-company-server/src/main/java/com/policy/company/module/";

    public static void main(String[] args) {
        ClientFlow f = new ClientFlow();
        f.ms = ms;
        f.filePath = clientPath;
        f.dName = yewu;
        f.xName = f.dName.substring(0, 1).toLowerCase().concat(f.dName.substring(1));
        f.path = f.filePath.substring(f.filePath.indexOf("com/policy")).replaceAll("/", ".");

        mkdir(f.filePath + "/api");
        f.apiPath = f.path + ".api";
        f.fileApiPath = f.filePath + "/api";
        mkdir(f.filePath + "/module");
        mkdir(f.filePath + "/module/" + f.xName);
        mkdir(f.filePath + "/module/" + f.xName + "/dto");
        f.dtoPath = f.path + ".module." + f.xName + ".dto";
        f.fileDtoPath = f.filePath + "/module/" + f.xName + "/dto";

        createClientDto(f);//创建dto
        createClientApi(f);//创建api

        ServerFlow s = new ServerFlow();
        s.dName = f.dName;
        s.xName = f.xName;
        s.ms = f.ms;
        s.filePath = serverPath;
        s.filePath += f.xName;
        s.path = s.filePath.substring(s.filePath.indexOf("com/policy")).replaceAll("/", ".");
        File dir = new File(s.filePath);
        if (!dir.exists()) {
            dir.mkdir();
        }

        //先生成bean
        createServerDomain(s);
        createServerMapper(s);
        createServerService(f, s);
        createServerApi(f, s);
        createServerServiceImpl(f, s);
    }

    private static void createServerServiceImpl(ClientFlow f, ServerFlow s) {
        s.fileServiceImplPath = s.filePath + "/service/impl";
        s.serviceImplPath = s.path + ".service.impl";
        new File(s.fileServiceImplPath).mkdir();
        String ff = "private final 1Mapper mapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public ResultPage<1DTO> selectPage(1SearchDTO input) {\n" +
                "        LambdaQueryWrapper<1> queryWrapper = new LambdaQueryWrapper<1>()\n" +
                "                .orderByDesc(1::getCreateTime);\n" +
                "        IPage<1> pageData = mapper.selectPage(new Page<>(input.getPage(), input.getSize()), queryWrapper);\n" +
                "        if (pageData.getRecords().isEmpty()) {\n" +
                "            return new ResultPage<>(new ArrayList<>(), 0);\n" +
                "        }\n" +
                "        ResultPage<1DTO> result = new ResultPage<>(new ArrayList<>(), (int) pageData.getTotal());\n" +
                "        for (1 item : pageData.getRecords()) {\n" +
                "            result.getData().add(toDto(item));\n" +
                "        }\n" +
                "        return result;\n" +
                "    }\n" +
                "\n" +
                "    private 1DTO toDto(1 item) {\n" +
                "        1DTO dto = new 1DTO();\n" +
                "        BeanUtils.copyProperties(item, dto);\n" +
                "        return dto;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<?> insert(1DTO input) {\n" +
                "        1 2 = new 1();\n" +
                "        BeanUtils.copyProperties(input, 2);\n" +
                "        mapper.insert(2);\n" +
                "        return Result.OK;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<?> update(1DTO input) {\n" +
                "        1 2 = new 1();\n" +
                "        BeanUtils.copyProperties(input, 2);\n" +
                "        if (mapper.updateById(2) > 0) {\n" +
                "            return Result.OK;\n" +
                "        } else {\n" +
                "            return Result.error(BaseResultCode.$000007);\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<1DTO> find(" + idType + " 2Id) {\n" +
                "        1 2 = mapper.selectById(2Id);\n" +
                "        if (2 == null) {\n" +
                "            return Result.error(BaseResultCode.$000008);\n" +
                "        }\n" +
                "        1DTO dto = new 1DTO();\n" +
                "        BeanUtils.copyProperties(2, dto);\n" +
                "        return Result.data(dto);\n" +
                "    }";
        String str = ("package " + s.serviceImplPath + ";\n" +
                getClassStr(f.classList) + getClassStr(s.classList) +
                "import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;\n" +
                "import com.baomidou.mybatisplus.core.metadata.IPage;\n" +
                "import com.tojoy.netco.component.common.base.BaseResultCode;\n" +
                "import com.baomidou.mybatisplus.extension.plugins.pagination.Page;\n" +
                "import com.tojoy.netco.web.inout.Result;\n" +
                "import com.tojoy.netco.web.inout.ResultPage;\n" +
                "import lombok.RequiredArgsConstructor;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import org.springframework.beans.BeanUtils;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "import java.util.ArrayList;\n" +

                "@Service\n " +
                "@RequiredArgsConstructor\n " +
                "@Slf4j\n " +
                "public class 1ServiceImpl implements 1Service {\n" +
                ff +
                "\n}"
        ).replaceAll("1", s.dName).replaceAll("2", s.xName);
        write(s.fileServiceImplPath + "/" + s.dName + "ServiceImpl.java", str);
    }

    private static void createServerApi(ClientFlow f, ServerFlow s) {
        s.fileApiPath = s.filePath + "/api";
        s.apiPath = s.path + ".api";
        new File(s.fileApiPath).mkdir();

        String ff = " private final 1Service service;\n" +
                "\n" +
                "    @Override\n" +
                "    public ResultPage<1DTO> selectPage(1SearchDTO input) {\n" +
                "        return service.selectPage(input);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<?> insert(1DTO input) {\n" +
                "        return service.insert(input);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<?> update(1DTO input) {\n" +
                "        return service.update(input);\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public Result<1DTO> find(" + idType + " 2Id) {\n" +
                "        return service.find(2Id);\n" +
                "    }";
        String str = ("package " + s.apiPath + ";\n" +
                "import " + f.apiPath + ".Micro1Api;\n" +
                "import " + f.dtoPath + ".1DTO;\n" +
                "import " + s.servicePath + ".1Service;\n" +
                "import " + f.dtoPath + ".1SearchDTO;\n" +
                "import com.tojoy.netco.web.inout.ResultPage;\n" +
                "import com.tojoy.netco.web.inout.Result;\n" +
                "import io.swagger.annotations.Api;\n" +
                "import io.swagger.annotations.ApiOperation;\n" +
                "import lombok.RequiredArgsConstructor;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n" +
                "@Api(\"3微服务实现\")\n" +
                "@RestController\n" +
                "@RequestMapping(Micro1Api.BASE)\n" +
                "@RequiredArgsConstructor\n" +
                "public class 1Api implements Micro1Api {\n" +
                ff +
                "\n}")
                .replaceAll("1", s.dName).replaceAll("2", s.xName).replaceAll("3", s.ms);
        write(s.fileApiPath + "/" + s.dName + "Api.java", str);
        s.classList.add("import " + s.apiPath + "." + s.dName + "Api;");
    }

    private static void createServerService(ClientFlow f, ServerFlow s) {
        String ff = "  /**\n" +
                "     * 分页查询\n" +
                "     *\n" +
                "     * @param input 参数\n" +
                "     * @return 结果\n" +
                "     */\n" +
                "    ResultPage<1DTO> selectPage(1SearchDTO input);\n" +
                "\n" +
                "    /**\n" +
                "     * 新增\n" +
                "     *\n" +
                "     * @param input 参数\n" +
                "     * @return 结果\n" +
                "     */\n" +
                "    Result<?> insert(1DTO input);\n" +
                "\n" +
                "    /**\n" +
                "     * 更新\n" +
                "     *\n" +
                "     * @param input 参数\n" +
                "     * @return 结果\n" +
                "     */\n" +
                "    Result<?> update(1DTO input);\n" +
                "\n" +
                "    /**\n" +
                "     * 查询\n" +
                "     *\n" +
                "     * @param 2Id 主键\n" +
                "     * @return 结果\n" +
                "     */\n" +
                "    Result<1DTO> find(" + idType + " 2Id);";


        s.fileServicePath = s.filePath + "/service";
        s.servicePath = s.path + ".service";
        new File(s.fileServicePath).mkdir();
        String str = ("package " + s.servicePath + ";\n" +
                getClassStr(f.classList) +
                "import com.tojoy.netco.web.inout.Result;\n" +
                "import com.tojoy.netco.web.inout.ResultPage;\n" +
                "public interface 1Service {\n" +
                ff +
                "}").replaceAll("1", s.dName).replaceAll("2", s.xName);
        write(s.fileServicePath + "/" + s.dName + "Service.java", str);
        s.classList.add("import " + s.servicePath + "." + s.dName + "Service;");
    }

    private static void createServerMapper(ServerFlow s) {
        s.fileMapperPath = s.filePath + "/mapper";
        s.mapperPath = s.path + ".mapper";
        new File(s.fileMapperPath).mkdir();
        String str = ("package " + s.mapperPath + ";\n" +
                "import com.baomidou.mybatisplus.core.mapper.BaseMapper;\n" +
                "import " + s.domainPath + "." + s.dName + ";\n" +
                " public interface 1Mapper extends BaseMapper<1> {\n}").replaceAll("1", s.dName);
        write(s.fileMapperPath + "/" + s.dName + "Mapper.java", str);
        s.classList.add("import " + s.mapperPath + "." + s.dName + "Mapper;");
    }

    private static void createServerDomain(ServerFlow s) {
        s.fileDomainPath = s.filePath + "/domain";
        s.domainPath = s.path + ".domain";
        new File(s.fileDomainPath).mkdir();
        String str = ("package " + s.domainPath + ";\n" +
                "import lombok.Data; \n" +
                "import java.time.LocalDate;\n" +
                "import java.time.LocalDateTime;\n" +
                "@Data \n" +
                "public class 1 {\n2}").replaceAll("1", s.dName).replaceAll("2", biao);
        write(s.fileDomainPath + "/" + s.dName + ".java", str);
        s.classList.add("import " + s.domainPath + "." + s.dName + ";");
    }

    private static void createClientApi(ClientFlow f) {
        String ffStr = " /**\n" +
                "     * 分页列表查询\n" +
                "     *\n" +
                "     * @param input 参数\n" +
                "     * @return list*/\n" +
                "    @PostMapping(\"/selectPage\")\n" +
                "    ResultPage<1DTO> selectPage(@RequestBody 1SearchDTO input);\n" +
                "\n" +
                "    /**\n" +
                "     * 新增\n" +
                "     *\n" +
                "     * @param input 参数\n" +
                "     * @return list\n" +
                "     */\n" +
                "    @PostMapping(\"/insert\")\n" +
                "    Result<?> insert(@RequestBody 1DTO input);\n" +
                "\n" +
                "    /**\n" +
                "     * 更新\n" +
                "     *\n" +
                "     * @param input 参数\n" +
                "     * @return list\n" +
                "     */\n" +
                "    @PostMapping(\"/update\")\n" +
                "    Result<?> update(@RequestBody 1DTO input);\n" +
                "\n" +
                "\n" +
                "    /**\n" +
                "     * 查询\n" +
                "     *\n" +
                "     * @param 2Id 参数\n" +
                "     * @return list\n" +
                "     */\n" +
                "    @GetMapping(\"/find/{2Id}\")\n" +
                "    Result<1DTO> find(@PathVariable(\"2Id\") " + idType + " 2Id);";

        String str = ("package " + f.apiPath + ";\n" +
                getClassStr(f.classList) +
                "import com.tojoy.netco.web.inout.Result;\n" +
                "import com.tojoy.netco.web.inout.ResultPage;\n" +
                "import io.swagger.annotations.Api;\n" +
                "import org.springframework.web.bind.annotation.GetMapping;\n" +
                "import org.springframework.web.bind.annotation.PathVariable;\n" +
                "import org.springframework.web.bind.annotation.PostMapping;\n" +
                "import org.springframework.web.bind.annotation.RequestBody;\n" +
                "@Api(\"3微服务接口\")\n" +
                "public interface Micro1Api {\n" +
                "    String BASE = \"2\";\n"
                +
                ffStr
                +
                "}\n"
        ).replaceAll("1", f.dName).replaceAll("2", f.xName).replaceAll("3", f.ms);
        write(f.fileApiPath + "/Micro" + f.dName + "Api.java", str);
        f.classList.add("import " + f.apiPath + "." + f.dName + "Api;");
    }

    private static String getClassStr(List<String> classList) {
        StringBuilder item = new StringBuilder();
        for (String s : classList) {
            item.append(s).append("\n");
        }
        return item.toString();
    }

    private static void createClientDto(ClientFlow f) {
        String str = ("package " + f.dtoPath + ";\n" +
                "import com.tojoy.netco.component.common.base.BasePageInput;\n" +
                "import lombok.Data;\n" +
                "import lombok.EqualsAndHashCode;\n" +
                "@EqualsAndHashCode(callSuper = true)\n" +
                "@Data\n" +
                "public class 1SearchDTO extends BasePageInput {\n}")
                .replaceAll("1", f.dName);
        write(f.fileDtoPath + "/" + f.dName + "SearchDTO.java", str);
        f.classList.add("import " + f.dtoPath + "." + f.dName + "SearchDTO;");

        str = ("package " + f.dtoPath + ";\n" +
                "import lombok.Data; \n" +
                "import java.time.LocalDate;\n" +
                "import java.time.LocalDateTime;\n" +
                "@Data \n" +
                "public class 1DTO {\n2}").replaceAll("1", f.dName).replaceAll("2", biao);
        write(f.fileDtoPath + "/" + f.dName + "DTO.java", str);
        f.classList.add("import " + f.dtoPath + "." + f.dName + "DTO;");
    }

    static void mkdir(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    static void write(String path, String str) {
        FileWriter fw = null;
        try {
            //创建字符输出流对象，负责向文件内写入
            fw = new FileWriter(path);
            //将str里面的内容读取到fw所指定的文件中
            fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}