package com.dosrobles.produccion.reports.templates;

import net.sf.dynamicreports.report.builder.ReportTemplateBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;

import java.util.Locale;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

public class ReportTemplates {

    public static final StyleBuilder rootStyle;
    public static final StyleBuilder boldStyle;
    public static final StyleBuilder italicStyle;
    public static final StyleBuilder boldCenteredStyle;
    public static final StyleBuilder bold12CenteredStyle;
    public static final StyleBuilder bold14CenteredStyle;
    public static final StyleBuilder bold16CenteredStyle;
    public static final StyleBuilder bold18CenteredStyle;
    public static final StyleBuilder columnStyle;
    public static final StyleBuilder columnTitleStyle;
    public static final StyleBuilder groupStyle;

    public static final ReportTemplateBuilder reportTemplate;
    public static final ComponentBuilder<?, ?> dynamicReportsComponent;

    static {
        rootStyle = stl.style().setPadding(2);
        boldStyle = stl.style(rootStyle).bold();
        italicStyle = stl.style(rootStyle).italic();
        boldCenteredStyle = stl.style(boldStyle).setTextAlignment(HorizontalTextAlignment.CENTER, VerticalTextAlignment.MIDDLE);
        bold12CenteredStyle = stl.style(boldCenteredStyle).setFontSize(12);
        bold14CenteredStyle = stl.style(boldCenteredStyle).setFontSize(14);
        bold16CenteredStyle = stl.style(boldCenteredStyle).setFontSize(16);
        bold18CenteredStyle = stl.style(boldCenteredStyle).setFontSize(18);
        columnStyle = stl.style(rootStyle).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE);
        columnTitleStyle = stl.style(columnStyle)
                .setBorder(stl.pen1Point())
                .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                .bold();
        groupStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);

        reportTemplate = template()
                .setLocale(new Locale("es", "NI"))
                .setColumnStyle(columnStyle)
        .setColumnTitleStyle(columnTitleStyle)
        .setGroupStyle(groupStyle)
        .setGroupTitleStyle(groupStyle)
        .ignorePagination().setIgnorePageWidth(true);

        dynamicReportsComponent = null;
    }

    public static ComponentBuilder<?,?> createTitleComponent(String title, String subtitle) {

        HorizontalListBuilder builder = cmp.horizontalList().add(cmp.text(title).setStyle(bold16CenteredStyle))
                .newRow();

        if (subtitle != null) {
            builder.add(cmp.text(subtitle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER));
        }

        return builder.add(cmp.verticalGap(10));
    }

}
