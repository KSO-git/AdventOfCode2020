package Day14;

import java.util.Map;

public class DataEntry {
    private String mask;
    private Map<Long, Long> memoryValueMap;
    private Map<Integer, Integer> memoryOriginalValue;

    public DataEntry() {
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public Map<Long, Long> getMemoryValueMap() {
        return memoryValueMap;
    }

    public void setMemoryValueMap(Map<Long, Long> memoryValueMap) {
        this.memoryValueMap = memoryValueMap;
    }

    public Map<Integer, Integer> getMemoryOriginalValue() {
        return memoryOriginalValue;
    }

    public void setMemoryOriginalValue(Map<Integer, Integer> memoryOriginalValue) {
        this.memoryOriginalValue = memoryOriginalValue;
    }
}
