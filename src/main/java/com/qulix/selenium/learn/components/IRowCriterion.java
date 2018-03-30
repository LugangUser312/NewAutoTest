package com.qulix.selenium.learn.components;

import org.openqa.selenium.WebElement;

/**
 * Критерий поиска строки в таблице
 */
public interface IRowCriterion {

    /**
     * Проверка соответствия строки таблицы данному критерию
     * @param row Строка таблицы
     * @return <code>true</code> если строка <code>row</code> соответствует текущему критерию
     */
    boolean matches(WebElement row);
}
