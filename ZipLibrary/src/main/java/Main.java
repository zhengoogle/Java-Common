import ir.mahdi.mzip.utils.AntZip;
import ir.mahdi.mzip.utils.ZipUtils;
import ir.mahdi.mzip.zip.ZipArchive;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ZipSolution.test();
    }
}

/**
 * Java zip test
 * ---Java zip > zip4j > apache ant
 */
class ZipSolution {
    private static final String zipFilePath = "G:\\FileServer\\ApkResource\\H5Plugin\\vue\\dist.zip";
    private static final String unzipFilePath = "G:\\FileServer\\ApkResource\\H5Plugin\\vue";

    public static void test() {
        zipZip4j();
        zipWithJava();
        zipApacheAnt();
    }

    /**
     * 方式1
     * -基于: java.util.zip
     * ---https://github.com/Blankj/AndroidUtilCode
     * ---https://github.com/SearchSunny/Android-zip-
     * summary:
     * ---#1.效率高，java.util.zip解压效率是zip4j的4倍
     * ---#2.支持.zip
     */
    public static void zipWithJava() {
        long startTime = System.currentTimeMillis();
        try {
            ZipUtils.unzipFile(zipFilePath, unzipFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.print("Java zip: " + costTime + "ms" + "\n");
    }

    /**
     * 方式2
     * -基于: zip4j
     * ---https://github.com/ghost1372/Mzip-Android
     * ---http://www.lingala.net/zip4j/
     * summary:
     * ---#1.解压效率比较低；
     * ---#2.支持.zip .rar；
     */
    public static void zipZip4j() {
        long startTime = System.currentTimeMillis();

        try {
            ZipArchive.unzip(zipFilePath, unzipFilePath, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.print("Zip4j: " + costTime + "ms" + "\n");
    }

    /**
     * 方式3
     * -基于: org.apache.tools.ant
     * ---http://ant.apache.org/bindownload.cgi
     * ---https://www.cnblogs.com/jecyhw/p/4531277.html
     * ---https://www.cnblogs.com/interdrp/p/6734033.html
     * summary:
     * ---#1.解压效率最低；
     * ---#2.支持.zip；
     */
    public static void zipApacheAnt() {
        long startTime = System.currentTimeMillis();

        try {
            AntZip.unzip(zipFilePath, unzipFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;
        System.out.print("ApacheAnt: " + costTime + "ms" + "\n");
    }
}