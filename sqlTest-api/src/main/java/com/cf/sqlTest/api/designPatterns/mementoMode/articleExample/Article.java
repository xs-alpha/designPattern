package com.cf.sqlTest.api.designPatterns.mementoMode.articleExample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Article {
    private String title;
    private String author;
    private String content;

    /*------------------------核心方法-------------------------*/

    public ArticleMemento saveToMemento(){
        return new ArticleMemento(title, author, content);
    }

    public void undoFromMemento(ArticleMemento am){
        this.author = am.getAuthor();
        this.title = am.getTitle();
        this.content = am.getContent();
    }
    /*-------------------------------------------------*/


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
