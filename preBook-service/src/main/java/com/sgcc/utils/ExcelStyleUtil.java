package com.sgcc.utils;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.styler.AbstractExcelExportStyler;
import cn.afterturn.easypoi.excel.export.styler.IExcelExportStyler;
import org.apache.poi.ss.usermodel.*;

public class ExcelStyleUtil extends AbstractExcelExportStyler implements IExcelExportStyler {

    private static final short FONT_SIZE_HEADER = 25;
    private static final short FONT_SIZE_TITLE = 12;
    private static final short FONT_SIZE_CONTENT = 10;

    public ExcelStyleUtil(Workbook workbook) {
        super.createStyles(workbook);
    }

    /**
     * 大标题样式设置
     * @param headerColor
     * @return
     */
    @Override
    public CellStyle getHeaderStyle(short headerColor) {
        CellStyle style = getBaseCellStyle(this.workbook);
        style.setFont(getFont(this.workbook, FONT_SIZE_HEADER,true));
        return style;
    }

    /**
     * 每列标题样式设置
     * @param titleColor
     * @return
     */
    @Override
    public CellStyle getTitleStyle(short titleColor) {
        CellStyle style = getBaseCellStyle(this.workbook);
        style.setFont(getFont(this.workbook, FONT_SIZE_TITLE,true));
        return style;
    }

    /**
     * 内容样式
     * @param noneStyler
     * @param entity
     * @return
     */
    @Override
    public CellStyle getStyles(boolean noneStyler, ExcelExportEntity entity) {
        CellStyle style = getBaseCellStyle(this.workbook);
        style.setFont(getFont(this.workbook, FONT_SIZE_CONTENT,false));
        return style;
    }

    /**
     * 基础样式
     *
     * @return
     */
    private CellStyle getBaseCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        //下边框
        style.setBorderBottom(BorderStyle.THIN);
        //左边框
        style.setBorderLeft(BorderStyle.THIN);
        //上边框
        style.setBorderTop(BorderStyle.THIN);
        //右边框
        style.setBorderRight(BorderStyle.THIN);
        //水平居中
        style.setAlignment(HorizontalAlignment.CENTER);
        //上下居中
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        //设置自动换行
        style.setWrapText(true);
        return style;
    }

    /**
     * 字体样式
     *
     * @param size   字体大小
     * @param isBold 是否加粗
     * @return
     */
    private Font getFont(Workbook workbook, short size, boolean isBold) {
        Font font = workbook.createFont();
        //字体样式
        font.setFontName("宋体");
        //是否加粗
        font.setBold(isBold);
        //字体大小
        font.setFontHeightInPoints(size);
        return font;
    }


}
