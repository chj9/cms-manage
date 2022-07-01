package com.chj9.cms.common.exception.status;

import com.chj9.cms.common.exception.CmsException;
import com.chj9.cms.common.util.VoidConsumer;

import java.util.function.Supplier;

/**
 * description: 错误处理默认方法
 *
 * @author liexuan
 * @date 2021-11-05 14:55
 **/
@SuppressWarnings("unused")
public interface Code extends Supplier<RuntimeException> {


    /**
     * 获取code 错误码
     *
     * @return String
     */
    String getCode();


    /**
     * 获取错误信息msg
     *
     * @return String
     */
    String getMsg();


    /**
     * 条件为true的时候抛出异常<br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.throwIfTrue(!Optional.ofNullable(a).isPresent())}
     * throw TurnRightException while Optional.ofNullable(a) is empty.
     *
     * @param throwIfTrue 条件为true的时候抛出异常
     * @throws Exception TurnRightException
     */
    default void throwIfTrue(boolean throwIfTrue) {
        if (throwIfTrue) {
            throw new CmsException(this);
        }
    }


    /**
     * 条件为true的时候抛出异常<br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.throwIfTrue(!Optional.ofNullable(a).isPresent())}
     * throw TurnRightException while Optional.ofNullable(a) is empty.
     *
     * @param throwIfTrue 条件为true的时候抛出异常
     * @param consumer    自定义方法体
     * @throws Exception TurnRightException
     */
    default void throwIfTrue(boolean throwIfTrue, VoidConsumer consumer) {
        if (throwIfTrue) {
            consumer.consume();
            throw new CmsException(this);
        }
    }


    /**
     * 条件为true的时候抛出异常<br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.throwIfTrue(!Optional.ofNullable(a).isPresent(), "xxx", obj)}
     * throw TurnRightException while Optional.ofNullable(a) is empty.
     *
     * @param throwIfTrue 条件为true的时候抛出异常
     * @param detail      自定义错误描述
     * @param payload     参数
     * @throws Exception TurnRightException
     */
    default void throwIfTrue(boolean throwIfTrue, String detail, Object payload) {
        if (throwIfTrue) {
            throw new CmsException(this, detail, payload);
        }
    }


    /**
     * 条件为false的时候抛出异常<br>
     * Throw TurnRightException if false.<br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.throwIfFalse(Optional.ofNullable(a).isPresent())}<br>
     * throw TurnRightException while Optional.ofNullable(a) is empty.
     *
     * @param throwIfFalse 条件为false的时候抛出异常
     * @param consumer     自定义方法体
     * @throws Exception TurnRightException
     */
    default <T> void throwIfFalse(boolean throwIfFalse, VoidConsumer consumer) {
        if (!throwIfFalse) {
            consumer.consume();
            throw new CmsException(this);
        }
    }


    /**
     * 条件为false的时候抛出异常<br>
     * Throw TurnRightException if false.<br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.throwIfFalse(Optional.ofNullable(a).isPresent())}<br>
     * throw TurnRightException while Optional.ofNullable(a) is empty.
     *
     * @param throwIfFalse 条件为false的时候抛出异常
     * @throws Exception TurnRightException
     */
    default void throwIfFalse(boolean throwIfFalse) {
        if (!throwIfFalse) {
            throw new CmsException(this);
        }
    }


    /**
     * 条件为false的时候抛出异常，可带自定义描述以及参数<br>
     * Throw TurnRightException if false.<br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.throwIfFalse(Optional.ofNullable(a).isPresent(), "xxx", obj)}<br>
     * throw TurnRightException while Optional.ofNullable(a) is empty.
     *
     * @param throwIfFalse 条件为true的时候抛出异常
     * @param detail       自定义错误描述
     * @param payload      参数
     * @throws Exception TurnRightException
     */
    default void throwIfFalse(boolean throwIfFalse, String detail, Object payload) {
        if (!throwIfFalse) {
            throw new CmsException(this, detail, payload);
        }
    }


    /**
     * 构造异常的写法<br>
     * <i>Example:</i><br>
     * {@code Optional.ofNullable(a).orElseThrow(ErrorCode.XXX)}
     *
     * @return TurnRightException
     */
    @Override
    default RuntimeException get() {
        return new CmsException(this);
    }


    @FunctionalInterface
    interface CodeWrapper<T> {
        /**
         * 自定义调用方法
         *
         * @return T
         * @throws Exception Exception
         */
        T call() throws Exception;
    }


    /**
     * 自定义方法 <br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.wrapper(() -> \{ some code \})}
     *
     * @param fn  The function you need to call.
     * @param <T> the value of fn
     * @return T
     * @throws Exception 异常
     */
    default <T> T wrapper(CodeWrapper<T> fn) {
        try {
            return fn.call();
        } catch (Exception e) {
            throw new CmsException(this);
        }
    }


    /**
     * 自定义实现方法，如果异常则抛出this <br>
     * <i>Example:</i><br>
     * {@code ErrorCode.XXX.wrapper(() -> \{ some code \}, "xxx", obj)}
     *
     * @param fn      自定义方法
     * @param detail  自定义错误描述
     * @param payload 参数
     * @return T
     * @throws Exception TurnRightException
     */
    default <T> T wrapper(CodeWrapper<T> fn, String detail, Object payload) {
        try {
            return fn.call();
        } catch (Exception e) {
            throw new CmsException(this, detail, payload);
        }
    }
}
