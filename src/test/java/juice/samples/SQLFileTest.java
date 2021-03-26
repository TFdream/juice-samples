package juice.samples;

import juice.util.IoUtils;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ricky Fung
 */
public class SQLFileTest {

    private static String updateSQL = "UPDATE `es_shop_goods` SET dispatch_type = 0, dispatch_price = 0, dispatch_id = %s WHERE id = %s;";

    public static void main(String[] args) {
        File file = new File("/Users/apple/Downloads/20210127_shop_5.sql");
        int count = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);

                String id = getGoodsId(line);
                String dispatchId = getDispatchId(line);
                String sql = String.format(updateSQL, dispatchId, id);
                System.out.println(sql);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtils.closeQuietly(br);
        }
        System.out.println("总数："+count);
    }

    private static String getGoodsId(String str) {
        Pattern p = Pattern.compile("VALUES \\('(\\d+)'", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }

    private static String getDispatchId(String str) {
        Pattern p = Pattern.compile("'(\\d+)'\\);", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group(1);
        }
        return null;
    }
}
