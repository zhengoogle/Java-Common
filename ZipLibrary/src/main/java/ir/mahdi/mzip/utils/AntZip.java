package ir.mahdi.mzip.utils;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;

import java.io.File;

public class AntZip {
    /**
     * zip压缩
     *
     * @param srcFileName 要压缩的文件名
     * @param zipFileName 压缩后的文件名
     */
    public static void zip(String srcFileName, String zipFileName) {
        File srcFile = new File(srcFileName);
        if (srcFile.exists()) {
            Project prj = new Project();

            Zip zip = new Zip();
            zip.setProject(prj);
            zip.setDestFile(new File(zipFileName));

            FileSet fileSet = new FileSet();
            fileSet.setProject(prj);
            fileSet.setDir(srcFile);
            zip.addFileset(fileSet);
            zip.execute();
        }
    }

    /**
     * unzip解压
     *
     * @param inputFileName 要解压的文件名
     * @param unZipFileName 解压的目录
     */
    public static void unzip(String inputFileName, String unZipFileName) {
        File srcFile = new File(inputFileName);
        if (srcFile.exists()) {
            Project prj = new Project();
            Expand expand = new Expand();
            expand.setProject(prj);
            expand.setSrc(srcFile);
            expand.setDest(new File(unZipFileName));
            expand.execute();
        }
    }
}
