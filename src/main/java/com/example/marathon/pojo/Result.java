package com.example.marathon.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {

    private Integer code; // 状态码
    private String message; // 提示信息
    private T data; // 业务数据

    // --- 静态工厂方法 ---

    /**
     * 成功-携带数据
     * (泛型方法 <T> 确保类型安全)
     */
    public static <T> Result<T> success(T data) {
        // 使用 new Result<>(...) 确保类型安全
        return new Result<>(200, "操作成功", data);
    }

    /**
     * 成功-不携带数据
     */
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    /**
     * 失败
     * (泛型方法 <T> 确保类型安全)
     * * 优化点：这里返回 Result<T> 而不是 Result。
     * 这样在 Controller 中调用时，Java 才能正确推断出
     * Result.error(...) 的完整类型是 Result<User>。
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(-1, message, null);
    }
}