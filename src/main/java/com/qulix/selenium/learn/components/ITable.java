package com.qulix.selenium.learn.components;

import org.openqa.selenium.WebElement;

/**
 * Created by RakitskyAK on 30.03.2018.
 */
public interface ITable {
    /**
     * Найти строку на текущей странице
     * @param criterion критерий поиска строки
     * @return Первую найденную строку, которая соответствует критерию. Либо null, если таких строк нет
     */
    WebElement findRowOnThisPage(IRowCriterion criterion);
    /**
     * Найти строку на всех страницах таблицы
     * @param criterion критерий поиска строки
     * @return Первую найденную строку (на любой странице), которая соответствует критерию. Либо null, если таких строк нет
     */
    WebElement findRowOnAllPages(IRowCriterion criterion);

    /**
     * Перейти на следующую страницу
     * @return <code>true</code> если переход возможен (мы не на последней странице) и выполнен
     */
    boolean toNextPage();
    /**
     * Перейти на предыдущую страницу
     * @return <code>true</code> если переход возможен (мы не на первой странице) и выполнен
     */
    boolean toPreviousPage();
    /**
     * Перейти на последнюю страницу
     * @return <code>true</code> если переход возможен (мы не на последней странице) и выполнен
     */
    boolean toLastPage();
    /**
     * Перейти на первую страницу
     * @return <code>true</code> если переход возможен (мы не на первой странице) и выполнен
     */
    boolean toFirstPage();
}
