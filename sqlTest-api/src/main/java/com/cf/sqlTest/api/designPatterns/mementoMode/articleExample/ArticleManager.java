package com.cf.sqlTest.api.designPatterns.mementoMode.articleExample;

import java.util.Stack;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class ArticleManager {
    private static final Stack<ArticleMemento> as = new Stack<>();

    public void addArticleMemento(ArticleMemento at){
        as.push(at);
    }

    public ArticleMemento getArticleMemento(){
        return as.pop();
    }

}
