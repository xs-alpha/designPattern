package com.cf.sqlTest.api.designPatterns.mementoMode.articleExample;

import lombok.AllArgsConstructor;

/**
 * @author: lpy
 * @Date: 2023/10/27
 * @desc: 注意memento是窄接口，只提供访问，不提供修改，在《大话设计模式》里有提及
 */
@AllArgsConstructor
public class ArticleMemento {
    private String title;
    private String author;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "ArticleMemento{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
