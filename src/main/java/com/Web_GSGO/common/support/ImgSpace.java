package com.Web_GSGO.common.support;

public class ImgSpace {

    private static final String USER_ID_SPACE = "userid";
    private static final String BNAME_IMAGE = "tumor_img";
    private static final String BNAME_FILE = "bname-file";
    private static final String OTHER_IMG_SPACE = "otherimage";
    private static final String SHOP_HEADER_IMG = "shopheadimg";
    private static final String HAND_ID_IMG = "handidimg";
    private static final String REGIST_TEST = "registtest";
    private static final String USER_VOICE="uservoice";

    public static String getSpace(Integer type) {
        if (type == 0) {
            return BNAME_FILE;
        } else if (type == 1) {
            return BNAME_IMAGE;
        } else if (type == 2) {
            return USER_ID_SPACE;
        } else if (type == 3) {
            return SHOP_HEADER_IMG;
        } else if (type == 4) {
            return HAND_ID_IMG;
        } else if (type == 5) {
            return REGIST_TEST;
        } else if(type == 6){
            return USER_VOICE;
        }else{
            return OTHER_IMG_SPACE;
        }
    }
}
