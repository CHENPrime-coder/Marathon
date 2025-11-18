package com.example.marathon.pojo;

/**
 * id: 1 -> 已完成
 * id: 2 -> 未完成
 */
public enum ResultStatus {
    COMPLETED(1, "已完成"),
    NOT_COMPLETED(2, "未完成");

    private final Integer id;
    private final String description;

    ResultStatus(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public static String getResultStatus(Integer id) {
        for (ResultStatus status : ResultStatus.values()) {
            if (status.getId().equals(id)) {
                return status.getDescription();
            }
        }
        throw new IllegalArgumentException("Invalid ResultStatus id: " + id);
    }
}
