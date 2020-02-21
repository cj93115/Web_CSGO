package com.Web_GSGO.common;


import com.Web_GSGO.common.util.Resources;

/**
 * @author lianglifeng
 * @version 2018年11月23日
 */
public enum HttpCode {
    /**
     * 200请求成功
     */
    SUCCESS(200),

    /**
     * 登录成功
     */
    LOGIN_SUCCESS(200,1),

    /**
     * 验证码发送成功
     */
    SEND_CODE_SUCCESS(200,2),

    /**
     *
     */
    PUSH_SUCCESS(200,3),

    /**
     * 注册成功
     */
    REGIST_SUCCESS(200,4),

    /**
     * 个人信息修改成功
     */
    INFO_EDIT_SUCCESS(200,5),

    /**
     * 密码修改成功
     */
    PASSWORD_EDIT_SUCCESS(200,6),

    /**
     * 用户删除成功
     */
    USER_DELETE_SUCCESS(200,7),

    /**
     * 删除成功
     */
    DELETE_SUCCESS(200,8),

    /**
     * 修改成功
     * */
    EDIT_SUCCESS(200,9),

    /**
     * 数据新增成功
     */
    ADD_SUCCESS(200,10),

    /**
     * 举报成功
     */
    COMPLAIN_SUCCESS(200,11),

    /**
     * 举报处理成功
     */
    COMPLAIN_UPDATE_SUCCESS(200,12),

    /**
     * 投诉类型添加成功
     */
    COMPLAIN_INSERT_TYPE(200,13),
    /**
     * 投诉类型修改成功
     */
    COMPLAIN_UPDATE_TYPE(200,14),

    /**
     * 已取消点赞
     */
    UPVOTE_DELETE_OK(200,15),

    /**
     * 回复成功
     */
    REPLY_OK(200,16),

    /**
     * 删除地址成功
     */
    DELETE_ADDRESS_SUCCESS(200,17),

    /**
     * 点赞成功
     */
    UPVOTE_SUCCESS(200,18),

    /**
     * 加入圈子成功
     */
    JOIN_COMMUNITY_SUCCESS(200,19),

    /**
     * 退出成功
     */
    QUIT_COMMUNITY_SUCCESS(200,20),

    /**
     * 没有查询到相关数据
     */
    DATA_NO_FOUND(200,21),





    /**
     * 400请求参数出错
     */
    BAD_REQUEST(400),

    /**
     * 消息推送失败
     */
    PUSH_FAIL(400,1),

    /**
     * 已被锁定
     */
    LOCKED(400,2),

    /**
     * 注册失败
     */
    REGIST_FAIL(400,3),

    /**
     * 登录失败
     */
    LOGIN_FAIL(400,4),
    /**
     * 频繁操作
     */
    MULTI_STATUS(400,5),

    /**
     * 获取用户信息失败，请检查openid和access_token是否正确
     */
    QQ_NODATA(400,6),
    /**
     * 获取微信用户信息失败，请检查openid和access_token是否正确
     */
    WEIXIN_NODATA(400,7),
    /**
     * 获取微博用户信息失败，请检查domain和access_token是否正确
     */
    WEIBO_NODATA(400,8),

    /**
     * 输入信息错误
     */
    ERROR_MESSAGE(400,9),
    /**
     * 验证码超出发送上限
     */
    OVER_SEND_MESSAGE(400,10),
    /**
     * 验证码错误
     */
    ERROR_VERIFY(400,11),
    /**
     * 验证码已超时失效
     */
    ERROR_VERIFY_CODE(400,12),

    /**
     * 用户不能为空
     */
    USER_IS_NOT_NULL(400,13),
    /**
     * 密码不能为空
     */
    PASSWORD_IS_NOT_NULL(400,14),
    /**
     * ID不能为空
     */
    ID_IS_NOT_NULL(400,15),

    /**
     * 手机号码不能为空
     */
    PHONE_IS_NOT_NULL(400,16),
    /**
     * 非法的手机号
     */
    ERROR_PHONE(400,17),

    /**
     * 非法的邮箱
     */
    ERROR_EMAIL(400,18),

    /**
     * 圈子有用户存在，不能删除
     */
    ERROR_DELETE_GROUP(400,19),

    /**
     * 对方已经在好友列表
     */
    FRIEND_ALREADY_EXIST(400,20),

    /**
     * 用户不存在或用户名错误
     */
    ERROR_USER(400,21),
    /**
     * 用户已注册
     */
    USER_IS_REG(400,22),

    /**
     * 邮箱或手机已注册
     */
    EMAIL_IS_REG(400,23),

    /**
     * 输入密码或验证码
     */
    INPUT_PASSWORD(400,24),
    /**
     * 用户不存在
     */
    USER_IS_NONE(400,25),
    /**
     * 用户密码错误
     */
    PASSWORD_IS_ERROR(400,26),

    /**
     * 验证码发送失败
     */
    SEND_CODE_ERROR(400,27),

    /**
     * 删除失败
     */
    ERROR_DELETE(400,28),

    /**
     * 您已举报过该内容，我们将会核实并处理
     */
    CONTYPE_IS_REPEAT(400,29),
    /**
     * 未查到该投诉数据
     */
    COMPLAIN_IS_NODATA(400,30),
    /**
     * 未查到该业务数据
     */
    RELEASEINFO_IS_NODATA(400,31),
    /**
     * 未查到该业务内容的用户数据
     */
    BIZ_USER_NODATA(400,32),
    /**
     * 未查到角色管理员的用户数据
     */
    ROLE_USER_NODATA(400,33),

    /**
     * 查找不到此投诉类型
     */
    COMPLAIN_TYPE_NODATA(34),

    /**
     * 添加的投诉类型不能为空
     */
    COMPLAIN_NAME_NODATA(400,35),

    /**
     * 已取消点赞
     */
    UPVOTE_DELETE(400,36),

    /**
     * 没有发布内容
     */
    RELEASE_NODATA(400,37),

    /**
     * 你已经点过赞
     */
    UPVOTE_ERROR(400,38),

    /**
     * 你已经踩过啦
     */
    TUCAO_ERROR(400,39),

    /**
     * 回复内容不能为空
     */
    REPLY_CONTENT_ISNOTNULL(400,40),

    /**
     * 重复加入圈子
     */
    HAS_JOINED_COMMUNITY(400,41),

    /**
     * 该订单已经支付过了
     */
    PAY_EXISTENCE(400,42),

    /**
     * 该支付ID不存在
     */
    PAYID_NO_EXISTENCE(400,43),
    /**
     * 芝麻验证已通过，无需重复验证
     */
    ZHIMA_YANZHENG_CHONGFU(400,44),
    /**
     * 该订单尚未创建
     */
    ORDER_NO_EXISTENCE(400,45),
    /**
     * 不存在的商品
     */
    GOODS_NO_DATA(400,46),
    /**
     * 该订单尚未支付
     */
    ORDER_NO_PAY(400,47),
    /**
     * 订单已经发货并完成交易，不能重复执行
     */
    ORDER_IS_END(400,48),
    /**
     * 密钥错误
     */
    KEY_ERROR(400,49),
    /**
     * 别名不存在
     */
    ALIAS_NO_DATA(400,50),
    /**
     * 别名已存在
     */
    ALIAS_IS_DATA(400,51),
    /**
     * 请先去芝麻验证，再来查询
     */
    ZHIMA_NO_FAQI(400,52),
    /**
     * 你已经收藏，无须重复收藏
     */
    COLLECT_ERROR(400,53),
    /**
     * 别名槽ID错误
     */
    ALIAS_SHUAXIN_NO(400,54),
    /**
     * 一天只能免费修改别名1次
     */
    ALIAS_UPDATE_NO(400,55),
    /**
     * 收藏失败
     */
    ERROR_COLLECT(400,56),
    /**
     * 点赞或取消失败
     */
    ERROR_UPVOTE(400,57),

    /**
     *  预设信息不一致
     */
    INFO_DIFF(400,58),

    /**
     * 电话已注册
     */
    PHONE_IS_REG(400,59),
    /**
     * 今天已经签到
     */
    YI_QIANDAO(400,60),
    /**
     * 评论失败
     */
    REPLY_ERROR(400,61),
    /**
     * 已关注
     */
    IS_LIKE(400,62),
    /**
     * 此别名已被使用
     */
    ALIAS_NO_REDIS(400,63),
    /**
     * 修改失败
     */
    UPDATE_ERROR(400,64),
    /**
     * 该版本号已经发行
     */
    VERSION_NUB_ERROR(400,65),




    /**
     * 401未授权
     */
    UNAUTHORIZED(401),
    /**
     * 403没有权限
     */
    FORBIDDEN(403),
    /**
     * 404找不到页面
     */
    NOT_FOUND(404),
    /**
     * 408请求超时
     */
    REQUEST_TIMEOUT(408),
    /**
     * 409发生冲突
     */
    CONFLICT(409),
    /**
     * 410已被删除
     */
    GONE(410),






    /**
     * ,
     * 500服务器出错
     */
    INTERNAL_SERVER_ERROR(500);










    private final Integer value;

    private final Integer subValue;

    private HttpCode(Integer value) {
        this.value = value;
        this.subValue = -1;
    }

    private HttpCode(Integer value, Integer subValue) {
        this.value = value;
        this.subValue = subValue;
    }

    public Integer value() {
        return this.value;
    }

    public Integer subValue() {
        return this.subValue;
    }

    public String msg() {
        return Resources.getMessage("HTTPCODE_" + this.value + (this.subValue == -1 ? "" : ("_" + this.subValue)));
    }

    public String getMsgByValue(Integer value,Integer subValue){
        return Resources.getMessage("HTTPCODE_" + value + (subValue == -1 ? "" : ("_" + subValue)));
    }
}
