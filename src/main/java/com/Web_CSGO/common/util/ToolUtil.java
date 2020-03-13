/**
 * Copyright (c) 2015-2016, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.Web_CSGO.common.util;

import com.Web_CSGO.common.HttpCode;
import com.Web_CSGO.common.support.StrKit;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

/**
 * 高频方法集合类
 */
@Slf4j
public class ToolUtil {

    /**
     * 获取随机位数的字符串
     *
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }



    /**
     * 获取异常的具体信息
     *
     */
    public static String getExceptionMsg(Exception e) {
        StringWriter sw = new StringWriter();
        try {
            e.printStackTrace(new PrintWriter(sw));
        } finally {
            try {
                sw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return sw.getBuffer().toString().replaceAll("\\$", "T");
    }

    /**
     * 比较两个对象是否相等。<br>
     * 相同的条件有两个，满足其一即可：<br>
     * 1. obj1 == null && obj2 == null; 2. obj1.equals(obj2)
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return 是否相等
     */
    public static boolean equals(Object obj1, Object obj2) {
        return (obj1 != null) ? (obj1.equals(obj2)) : (obj2 == null);
    }

    /**
     * 计算对象长度，如果是字符串调用其length函数，集合类调用其size函数，数组调用其length属性，其他可遍历对象遍历计算长度
     *
     * @param obj 被计算长度的对象
     * @return 长度
     */
    public static int length(Object obj) {
        if (obj == null) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length();
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).size();
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size();
        }

        int count;
        if (obj instanceof Iterator) {
            Iterator<?> iter = (Iterator<?>) obj;
            count = 0;
            while (iter.hasNext()) {
                count++;
                iter.next();
            }
            return count;
        }
        if (obj instanceof Enumeration) {
            Enumeration<?> enumeration = (Enumeration<?>) obj;
            count = 0;
            while (enumeration.hasMoreElements()) {
                count++;
                enumeration.nextElement();
            }
            return count;
        }
        if (obj.getClass().isArray() == true) {
            return Array.getLength(obj);
        }
        return -1;
    }

    /**
     * 对象中是否包含元素
     *
     * @param obj     对象
     * @param element 元素
     * @return 是否包含
     */
    public static boolean contains(Object obj, Object element) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof String) {
            if (element == null) {
                return false;
            }
            return ((String) obj).contains(element.toString());
        }
        if (obj instanceof Collection) {
            return ((Collection<?>) obj).contains(element);
        }
        if (obj instanceof Map) {
            return ((Map<?, ?>) obj).values().contains(element);
        }

        if (obj instanceof Iterator) {
            Iterator<?> iter = (Iterator<?>) obj;
            while (iter.hasNext()) {
                Object o = iter.next();
                if (equals(o, element)) {
                    return true;
                }
            }
            return false;
        }
        if (obj instanceof Enumeration) {
            Enumeration<?> enumeration = (Enumeration<?>) obj;
            while (enumeration.hasMoreElements()) {
                Object o = enumeration.nextElement();
                if (equals(o, element)) {
                    return true;
                }
            }
            return false;
        }
        if (obj.getClass().isArray() == true) {
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                Object o = Array.get(obj, i);
                if (equals(o, element)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 对象是否不为空(新增)
     *
     * @param obj String,List,Map,Object[],int[],long[]
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        return !isEmpty(o);
    }

    /**
     * 对象是否为空
     *
     * @param obj String,List,Map,Object[],int[],long[]
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if (o instanceof String) {
            if (o.toString().trim().equals("")) {
                return true;
            }
        } else if (o instanceof List) {
            if (((List) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Map) {
            if (((Map) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Set) {
            if (((Set) o).size() == 0) {
                return true;
            }
        } else if (o instanceof Object[]) {
            if (((Object[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof int[]) {
            if (((int[]) o).length == 0) {
                return true;
            }
        } else if (o instanceof long[]) {
            if (((long[]) o).length == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否存在 Empty Object
     *
     * @param os 对象组
     * @return
     */
    public static boolean isOneEmpty(Object... os) {
        for (Object o : os) {
            if (isEmpty(o)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 对象组中是否全是 Empty Object
     *
     * @param os
     * @return
     */
    public static boolean isAllEmpty(Object... os) {
        for (Object o : os) {
            if (!isEmpty(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否为数字
     *
     * @param obj
     * @return
     */
    public static boolean isNum(Object obj) {
        try {
            Integer.parseInt(obj.toString());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 如果为空, 则调用默认值
     *
     * @param str
     * @return
     */
    public static Object getValue(Object str, Object defaultValue) {
        if (isEmpty(str)) {
            return defaultValue;
        }
        return str;
    }



    /**
     * 格式化文本
     *
     * @param template 文本模板，被替换的部分用 {key} 表示
     * @param map      参数值对
     * @return 格式化后的文本
     */
    public static String format(String template, Map<?, ?> map) {
        return StrKit.format(template, map);
    }

    /**
     * 强转->string,并去掉多余空格
     *
     * @param str
     * @return
     */
    public static String toStr(Object str) {
        return toStr(str, "");
    }

    /**
     * 强转->string,并去掉多余空格
     *
     * @param str
     * @param defaultValue
     * @return
     */
    public static String toStr(Object str, String defaultValue) {
        if (null == str) {
            return defaultValue;
        }
        return str.toString().trim();
    }

    /**
     * 强转->int
     *
     * @param obj
     * @return
     */
//	public static int toInt(Object value) {
//		return toInt(value, -1);
//	}

    /**
     * 强转->int
     *
     * @param obj
     * @param defaultValue
     * @return
     */
//	public static int toInt(Object value, int defaultValue) {
//		return Convert.toInt(value, defaultValue);
//	}

    /**
     * 强转->long
     *
     * @param obj
     * @return
     */
//	public static long toLong(Object value) {
//		return toLong(value, -1);
//	}

    /**
     * 强转->long
     *
     * @param obj
     * @param defaultValue
     * @return
     */
//	public static long toLong(Object value, long defaultValue) {
//		return Convert.toLong(value, defaultValue);
//	}
//
//	public static String encodeUrl(String url) {
//		return URLKit.encode(url, CharsetKit.UTF_8);
//	}
//
//	public static String decodeUrl(String url) {
//		return URLKit.decode(url, CharsetKit.UTF_8);
//	}

    /**
     * map的key转为小写
     *
     * @param map
     * @return Map<String , Object>
     */
    public static Map<String, Object> caseInsensitiveMap(Map<String, Object> map) {
        Map<String, Object> tempMap = new HashMap<>();
        for (String key : map.keySet()) {
            tempMap.put(key.toLowerCase(), map.get(key));
        }
        return tempMap;
    }

    /**
     * 获取map中第一个数据值
     *
     * @param <K> Key的类型
     * @param <V> Value的类型
     * @param map 数据源
     * @return 返回的值
     */
    public static <K, V> V getFirstOrNull(Map<K, V> map) {
        V obj = null;
        for (Entry<K, V> entry : map.entrySet()) {
            obj = entry.getValue();
            if (obj != null) {
                break;
            }
        }
        return obj;
    }

    /**
     * 创建StringBuilder对象
     *
     * @return StringBuilder对象
     */
    public static StringBuilder builder(String... strs) {
        final StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str);
        }
        return sb;
    }

    /**
     * 创建StringBuilder对象
     *
     * @return StringBuilder对象
     */
    public static void builder(StringBuilder sb, String... strs) {
        for (String str : strs) {
            sb.append(str);
        }
    }

    /**
     * 去掉指定后缀
     *
     * @param str    字符串
     * @param suffix 后缀
     * @return 切掉后的字符串，若后缀不是 suffix， 返回原字符串
     */
    public static String removeSuffix(String str, String suffix) {
        if (isEmpty(str) || isEmpty(suffix)) {
            return str;
        }

        if (str.endsWith(suffix)) {
            return str.substring(0, str.length() - suffix.length());
        }
        return str;
    }



    /**
     * 首字母大写
     *
     */
    public static String firstLetterToUpper(String val) {
        return StrKit.firstCharToUpperCase(val);
    }

    /**
     * 首字母小写
     *
     */
    public static String firstLetterToLower(String val) {
        return StrKit.firstCharToLowerCase(val);
    }

    /**
     * 判断是否是windows操作系统
     *
     */
    public static Boolean isWinOs() {
        String os = System.getProperty("os.name");
        if (os.toLowerCase().startsWith("win")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取临时目录
     *
     */
    public static String getTempPath() {
        return System.getProperty("java.io.tmpdir");
    }

    /**
     * 把一个数转化为int
     *
     */
    public static Integer toInt(Object val) {
        if (val instanceof Double) {
            BigDecimal bigDecimal = new BigDecimal((Double) val);
            return bigDecimal.intValue();
        } else {
            return Integer.valueOf(val.toString());
        }

    }

    /**
     * 获取项目路径
     */
    public static String getWebRootPath(String filePath) {
        try {
            String path = ToolUtil.class.getClassLoader().getResource("").toURI().getPath();
            path = path.replace("/WEB-INF/classes/", "");
            path = path.replace("/target/classes/", "");
            path = path.replace("file:/", "");
            if (ToolUtil.isEmpty(filePath)) {
                return path;
            } else {
                return path + "/" + filePath;
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文件后缀名 不包含点
     */
    public static String getFileSuffix(String fileWholeName) {
        if (ToolUtil.isEmpty(fileWholeName)) {
            return "none";
        }
        int lastIndexOf = fileWholeName.lastIndexOf(".");
        return fileWholeName.substring(lastIndexOf + 1);
    }
    /**
     * @params []
     * @return java.util.List<java.lang.String>
     * @description: 获取年级
     * @author hdf
     * @version 1.0
     * @since 1.0 2018/11/19
     */
    public static List<String> getGrade(int type){
        List<String> reList = new ArrayList<>();
        Date date = new Date();
        String yearStr = DateUtil.format(date,"yyyy");
        Integer yearInt = Integer.parseInt(yearStr);
        String monthStr = DateUtil.format(date,"MM");
        if(type == 3){
//            if(Integer.parseInt(monthStr)>8){
//                reList.add(String.valueOf(yearInt-2));
//                reList.add(String.valueOf(yearInt-1));
//                reList.add(yearStr);
//            }else{
//                reList.add(String.valueOf(yearInt-3));
//                reList.add(String.valueOf(yearInt-2));
//                reList.add(String.valueOf(yearInt-1));
//            }
            reList.add("2016");
            reList.add("2017");
            reList.add("2018");
        }else if(type == 4){
            if(Integer.parseInt(monthStr)>8){
//                reList.add(String.valueOf(yearInt-3));
            	reList.add(yearStr);
            	reList.add(String.valueOf(yearInt-1));
                reList.add(String.valueOf(yearInt-2));
            }else{
//                reList.add(String.valueOf(yearInt-4));
            	reList.add(String.valueOf(yearInt-1));
            	reList.add(String.valueOf(yearInt-2));
                reList.add(String.valueOf(yearInt-3));
            }
        }
        return reList;
    }

 /**
     * @params
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @description: 获取本周时间段
     * @author tangchao
     * @version 1.0
     * @since 1.0 2018/12/4
     */
    public static Map<String,Object> thisWeekDate(){
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=new GregorianCalendar();
        map.put("endDate", formater.format(cal.getTime()));
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.setTime(new Date());
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        Date first=cal.getTime();
        map.put("startDate", formater.format(first));
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_MONTH,-1);
        Date homeDate = cal1.getTime();
        if(homeDate.before(first)) {
            map.put("homeDate", formater.format(first));
        }else{
            map.put("homeDate", formater.format(homeDate));
        }
            return  map;
    }

    /**
     * @params
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @description: 获取本月时间段
     * @author tangchao
     * @version 1.0
     * @since 1.0 2018/12/4
     */
    public static Map<String,Object> thisMonthDate(){
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat formater=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal=new GregorianCalendar();
        map.put("endDate", formater.format(cal.getTime()));
        Calendar calendar1=Calendar.getInstance();
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println("本月第一天: "+formater.format(calendar1.getTime()));
        map.put("startDate", formater.format(calendar1.getTime()));
        Calendar cal1 = Calendar.getInstance();
        cal1.add(Calendar.DAY_OF_MONTH,-1);
        Date homeDate = cal1.getTime();
        if(homeDate.before(calendar1.getTime())) {
            map.put("homeDate", formater.format(calendar1.getTime()));
        }else{
            map.put("homeDate", formater.format(homeDate));
        }
        return  map;
    }
    /**
     * @params [dividend：被除数, divisor：除数]
     * @return java.math.BigDecimal
     * @description: 计算比例
     * @author hdf
     * @version 1.0
     * @since 1.0 2019/1/2
     */
    public static BigDecimal getPro(Object dividend,Object divisor){
        BigDecimal totalBig = new BigDecimal(divisor.toString());
        BigDecimal divBig = new BigDecimal(dividend.toString());
        BigDecimal reBig = new BigDecimal("0");
        if(totalBig.intValue() != 0){
            reBig = divBig.multiply(new BigDecimal("100")).divide(totalBig,3,BigDecimal.ROUND_CEILING);
        }
        return reBig;
    }

    /**
     * 文件保存
     * @param file
     * @param request
     * @return
     */
    public static String filesUpload(MultipartFile file) {
        File saveDir;
        String imgUrl="";
        // 判断文件是否为空
        if (!file.isEmpty())
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                //  String filePath =  request.getSession().getServletContext().getRealPath("/");//获取项目路径
                //  String substring = filePath.substring(0, 2);//分割盘符拿到根目录
                String substring = "D:";//分割盘符拿到根目录

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");//设置日期格式
                String realName = df.format(new Date()).toString().replaceAll("[\\p{P}+~$`^=|<>～｀＄＾＋＝｜＜＞￥×]", "")
                        .replace(" ", "");//取日期去掉空格和标点符号

                // 1 构建存放路径
                File fileImg = new File("/csgo_img");
                if (!fileImg.exists()) {
                    fileImg.mkdirs();//创建文件夹
                    // 2 创建提示文本文本
                    String pathTxt = substring + "/csgo_img/该文件为图片文件夹.txt";
                    //如果文件夹下没有 提示文件.txt 就会创建该文件
                    BufferedWriter bw = new BufferedWriter(new FileWriter(pathTxt));
                    bw.close();//一定要关闭文件
                }
                //图片尺寸不变，压缩图片文件大小outputQuality实现,参数1为最高质量
                String fileName = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));//获取文件后缀，更改文件名
                imgUrl = substring + "/csgo_img/" + realName + fileName;
                saveDir = new File(imgUrl);
                file.transferTo(saveDir);   // 转存文件
            } catch (Exception e) {
                log.error(e.getMessage());
                return null;
            }
        return imgUrl;
    }
}