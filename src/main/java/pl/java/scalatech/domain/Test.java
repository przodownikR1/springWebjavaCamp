package pl.java.scalatech.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.TimeZone;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class Test {

    private Locale jvmLocale = Locale.getDefault();
    private Locale locale = LocaleContextHolder.getLocale();
    private String jvmTimeZone = TimeZone.getDefault().getID();

    @DateTimeFormat(style = "S-")
    private LocalDate currentDate = LocalDate.now();
    @DateTimeFormat(style = "-S")
    private LocalDateTime currentTime = LocalDateTime.now();
    // @DateTimeFormat(style = "SS")
    private LocalTime currentDateTime = LocalTime.now();
    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal currency = new BigDecimal(99.99);
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private BigDecimal number = new BigDecimal(99.99);
    @NumberFormat(style = NumberFormat.Style.PERCENT)
    private BigDecimal percent = new BigDecimal(0.90);

}