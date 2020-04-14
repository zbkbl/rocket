package test1;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @description: test
 * @author: Liuyang
 * @date: 2018-11-06 17:22
 **/
public class TestLucene {
    public static void main(String[] args) throws IOException {
        String dirPath = "/Users/liuyang/Desktop/";
        String indexPath = "/usr/local/liuyang/luceneIndex";
        createIndex(dirPath,indexPath,false);
    }

    /**
     * 创建索引
     * @param dirPath 需要索引的文件目录
     * @param indexPath 索引存放目录
     * @param createOrAppend
     * @throws IOException
     */
    public static void createIndex(String dirPath,String indexPath,boolean createOrAppend) throws IOException {
        long startTime = System.currentTimeMillis();
        Directory directory = FSDirectory.open(Paths.get(indexPath, new String[0]));
        Path docDirPath = Paths.get(dirPath, new String[0]);
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);
        if(createOrAppend){
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }else{
            indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        }
        IndexWriter indexWriter = new IndexWriter(directory,indexWriterConfig);
        indexDocs(indexWriter,docDirPath);
        indexWriter.close();
        System.out.println(System.currentTimeMillis()-startTime);
    }

    /**
     * 根据文件路径对文件内容进行索引
     * 如果是目录则扫描目录下的文件
     * @param indexWriter
     * @param path
     * @throws IOException
     */
    public static void indexDocs(final IndexWriter indexWriter,Path path) throws IOException {
        if(Files.isDirectory(path,new LinkOption[0])){
            //目录
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    indexDoc(indexWriter, file, attrs.lastModifiedTime().toMillis());
                    return FileVisitResult.CONTINUE;
                }
            });
        } else {
            indexDoc(indexWriter, path, Files.getLastModifiedTime(path,new LinkOption[0]).toMillis());
        }
    }

    /**
     * 根据文件路径对文件内容进行索引
     * @param indexWriter
     * @param path
     * @throws IOException
     */
    public static void indexDoc(IndexWriter indexWriter,Path path,long lastModified) throws IOException {
        Document document = new Document();
        Field pathField = new StringField("path",path.toString(),Field.Store.YES);
        document.add(pathField);
        Field lastModifiedField = new LongField("modified",lastModified,Field.Store.NO);
        document.add(lastModifiedField);
        Field contentField = new TextField("content",new BufferedReader(
                new InputStreamReader(Files.newInputStream(path, new OpenOption[0]), StandardCharsets.UTF_8)
        ));
        document.add(contentField);
        if(indexWriter.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE){
            indexWriter.addDocument(document);
        }else{
            indexWriter.updateDocument(new Term("path", path.toString()), document);
        }
        indexWriter.commit();
    }

}
