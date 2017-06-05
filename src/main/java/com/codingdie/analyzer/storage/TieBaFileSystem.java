package com.codingdie.analyzer.storage;

import com.codingdie.analyzer.storage.spider.IndexSpiderTaskStorage;
import com.codingdie.analyzer.storage.spider.PostDetailStorage;
import com.codingdie.analyzer.storage.spider.PostIndexStorage;

import java.io.File;

/**
 * Created by xupeng on 2017/5/10.
 */
public class TieBaFileSystem {
    public  static  final int ROLE_MASTER=0;
    public  static  final int ROLE_SLAVE=1;

    private IndexSpiderTaskStorage indexSpiderTaskStorage;
    private PostIndexStorage postIndexStorage;
    private PostDetailStorage postDetailStorage;

    private File root;
    public TieBaFileSystem(String name,int role){
        this.root=new File("storage/"+name);
        if(!this.root.exists()){
            this.root.mkdirs();
        }
        if(role==ROLE_MASTER){
            File spiderTaskRootPath = new File(root.getAbsolutePath() + File.separatorChar + "spidertask");
            if(!spiderTaskRootPath.exists()){
                spiderTaskRootPath.mkdirs();
            }
            this.indexSpiderTaskStorage =new IndexSpiderTaskStorage(spiderTaskRootPath);
            File postIndexRootPath = new File(root.getAbsolutePath() + File.separatorChar + "postindex");
            if(!postIndexRootPath.exists()){
                postIndexRootPath.mkdirs();
            }
            this.postIndexStorage=new PostIndexStorage(postIndexRootPath);
        }
        if(role==ROLE_SLAVE){
            File postContentRootPath = new File(root.getAbsolutePath() + File.separatorChar + "postcontent");
            if(!postContentRootPath.exists()){
                postContentRootPath.mkdirs();
            }
            this.postDetailStorage =new PostDetailStorage(postContentRootPath);
        }
    }

    public IndexSpiderTaskStorage getIndexSpiderTaskStorage() {
        return indexSpiderTaskStorage;
    }

    public PostIndexStorage getPostIndexStorage() {
        return postIndexStorage;
    }

    public PostDetailStorage getPostDetailStorage() {
        return postDetailStorage;
    }

    public void clear(){
        this.root.delete();
    }

}