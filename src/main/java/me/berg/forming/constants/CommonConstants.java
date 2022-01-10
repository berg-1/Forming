package me.berg.forming.constants;

public interface CommonConstants {

    /**
     * 超级管理员id
     */
    Long SUPER_ADMIN_ID = 1L;
    /**
     * 用户正常状态
     * 1:正常 0 :禁用
     */
    Integer USER_NORMAL_STATUS = 1;
    /**
     * 文件下载content_type
     */
    String FILE_DOWNLOAD_CONTENT_TYPE = "application/octet-stream;charset=UTF-8";

    /**
     * 数字常量
     */
    interface ConstantNumber {

        Integer NEGATIVE = -1;

        Integer ZERO = 0;

        Integer ONE = 1;

        Integer TOW = 2;

        Integer THREE = 3;

        Integer FOUR = 4;

        Integer FIVE = 5;

        Integer SIX = 6;

        Integer Seven = 7;

        Integer Eight = 8;

        Integer Nine = 9;

        Integer Ten = 10;

    }
}
