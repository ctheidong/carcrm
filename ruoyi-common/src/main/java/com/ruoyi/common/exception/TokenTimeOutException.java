package com.ruoyi.common.exception;

/**
 * 业务异常
 * 
 * @author ruoyi
 */
public class TokenTimeOutException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public TokenTimeOutException(String message)
    {
        this.message = message;
    }

    public TokenTimeOutException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
