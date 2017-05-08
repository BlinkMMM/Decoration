package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 该系统的日期转换类
 * 日期与文本的相互转化
 * @author Blink
 *
 */
public class DateUtil {
    /**
     * 日期格式：yyyy.MM.dd HH:mm:ss
     */
    public static final String DATE_PATTERN_YMDHMS = "yyyy.MM.dd HH:mm:ss";
    /**
     * 日期格式：yyyy.MM.dd
     */
    public static final String DATE_PATTERN_YMD = "yyyy-MM-dd";
    /**
     * 日期格式：HH:mm:ss
     */
    public static final String DATE_PATTERN_HMS = "HH:mm:ss";
    /**
     * 日期格式：HH:mm
     */
    public static final String DATE_PATTERN_HM = "HH:mm";
    
    /**
     * 将当前时间格式化成文本显示
     * @param date 源日期对象 
     * @param pattern 要显示出的文本格式
     * @return 返回文本形式的日期
     */
    public static String date2String(Date date,String pattern){ 
        return new SimpleDateFormat(pattern).format(date);
    }
    /**
     * 将字符串解析成Date对象
     * @param strDate 源文本形式的日期
     * @param pattern 要被转换的文本格式
     * @return 返回日期对象
     */
    public static Date  string2Date(String strDate,String pattern){
        Date date = null;
        try {
             date = new SimpleDateFormat(pattern).parse(strDate);
        } catch (ParseException e) {
          
            e.printStackTrace();
        }
        return date;
    }
}
