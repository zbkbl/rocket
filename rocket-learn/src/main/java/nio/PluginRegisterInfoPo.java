package nio;

import java.util.Date;

/**
 * @author liuyang
 * @description 插件对象
 * @date 2020/06/29 14:32
 **/
public class PluginRegisterInfoPo {

    private Integer id;
    private String pluginName;
    private Integer type;
    private String interfaceName;
    private String implementation;
    private Integer rcdType;
    private Integer rcdProduct;
    private Integer pluginMode;
    private String zkAddr;
    private String groupName;
    private String version;
    private String protocol;
    private Date addTime;
    private String author;
    private Integer timeout;
    private String providerEnv;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getImplementation() {
        return implementation;
    }

    public void setImplementation(String implementation) {
        this.implementation = implementation;
    }

    public Integer getRcdType() {
        return rcdType;
    }

    public void setRcdType(Integer rcdType) {
        this.rcdType = rcdType;
    }

    public Integer getRcdProduct() {
        return rcdProduct;
    }

    public void setRcdProduct(Integer rcdProduct) {
        this.rcdProduct = rcdProduct;
    }

    public Integer getPluginMode() {
        return pluginMode;
    }

    public void setPluginMode(Integer pluginMode) {
        this.pluginMode = pluginMode;
    }

    public String getZkAddr() {
        return zkAddr;
    }

    public void setZkAddr(String zkAddr) {
        this.zkAddr = zkAddr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getProviderEnv() {
        return providerEnv;
    }

    public void setProviderEnv(String providerEnv) {
        this.providerEnv = providerEnv;
    }

    @Override
    public String toString() {
        return "PluginRegisterInfoPo{" +
                "id=" + id +
                ", pluginName='" + pluginName + '\'' +
                ", type=" + type +
                ", interfaceName='" + interfaceName + '\'' +
                ", implementation='" + implementation + '\'' +
                ", rcdType=" + rcdType +
                ", rcdProduct=" + rcdProduct +
                ", pluginMode=" + pluginMode +
                ", zkAddr='" + zkAddr + '\'' +
                ", groupName='" + groupName + '\'' +
                ", version='" + version + '\'' +
                ", protocol='" + protocol + '\'' +
                ", addTime=" + addTime +
                ", author='" + author + '\'' +
                ", timeout=" + timeout +
                ", providerEnv='" + providerEnv + '\'' +
                '}';
    }
}
