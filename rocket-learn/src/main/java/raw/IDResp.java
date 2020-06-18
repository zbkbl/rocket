package raw;

import java.util.Set;

/**
 * @author liuyang
 * @description
 * @date 2020/05/19 10:05
 **/
public class IDResp<K> {
    private Set<K> idSet;


    public IDResp(Set<K> idSet) {
        this.idSet = idSet;
    }

    public Set<K> getIdSet() {
        return idSet;
    }

    public void setIdSet(Set<K> idSet) {
        this.idSet = idSet;
    }
}
