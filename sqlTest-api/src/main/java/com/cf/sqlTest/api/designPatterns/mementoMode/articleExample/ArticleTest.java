package com.cf.sqlTest.api.designPatterns.mementoMode.articleExample;

/**
 * @author: lpy
 * @Date: 2023/10/27
 */
public class ArticleTest {
    public static void main(String[] args) {
        ArticleManager manager = new ArticleManager();

        Article article = new Article("文章A", "xiaosheng", "简单工厂模式");
        ArticleMemento memento = article.saveToMemento();
        manager.addArticleMemento(memento);
        System.out.println("快照1："+article.toString());

        article.setTitle("文章B");
        article.setContent("外观模式");
        memento = article.saveToMemento();
        manager.addArticleMemento(memento);
        System.out.println("快照2："+article.toString());

        article.setTitle("文章C");
        article.setContent("状态模式");
        memento = article.saveToMemento();
        manager.addArticleMemento(memento);
        System.out.println("快照3："+article.toString());

        article.setTitle("摆烂了");
        memento = manager.getArticleMemento();
        article.undoFromMemento(memento);
        System.out.println("恢复1："+article.toString());

        article.setTitle("摆烂了2");
        memento = manager.getArticleMemento();
        article.undoFromMemento(memento);
        System.out.println("恢复2："+article.toString());
    }
}
