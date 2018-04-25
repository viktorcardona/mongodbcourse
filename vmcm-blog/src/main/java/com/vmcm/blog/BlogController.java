package com.vmcm.blog;

/**
 * Created by viccardo on 27/03/16.
 */
public class BlogController {

    public static void main(String[] args) {

        if(args.length==0){
            new BlogController("mongodb://localhost");
        }else{
            new BlogController(args[0]);
        }
    }

    public BlogController(String mongoURIString){

    }

}
