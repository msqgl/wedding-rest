package com.msqgl.app.service;

import com.google.common.io.Files;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.msqgl.app.dao.GiftDao;
import com.msqgl.app.dao.MsgDao;
import com.msqgl.app.model.Gift;
import com.msqgl.app.model.Msg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.List;

@Service
public class PDFService {

  private static final Logger LOG = LoggerFactory.getLogger(PDFService.class);

  private static final int LOGO_WIDTH = 100;

  private static final Font FONT_TITLE = new Font(Font.HELVETICA, 14, Font.BOLD);
  private static final Font FONT_SUBTITLE = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);
  private static final Font FONT_TABLE_HEADER = new Font(Font.HELVETICA, Font.DEFAULTSIZE, Font.BOLD);

  private static final Color GREY = new Color(226, 226, 226);

  @Autowired
  private GiftDao giftDao;

  @Autowired
  private MsgDao msgDao;

  public void createPDF(BufferedOutputStream bufferedOutputStream) throws FileNotFoundException, DocumentException {
    Document document = new Document(PageSize.A4.rotate());
    PdfWriter.getInstance(document, bufferedOutputStream);
    document.open();
    addLogo(document);
    addTitle(document);
    addMainContent(document);
    document.close();
  }

  private void addMainContent(final Document document) throws DocumentException {
    final List<Msg> allMsg = msgDao.getAllMsg();
    PdfPTable table = new PdfPTable(4);
    table.setWidthPercentage(100);
    table.setWidths(new int[]{25, 25, 25, 25});
    PdfPCell cell;

    cell = new PdfPCell(new Phrase("Regalo", FONT_TABLE_HEADER));
    cell.setBackgroundColor(GREY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Nome", FONT_TABLE_HEADER));
    cell.setBackgroundColor(GREY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Messaggio", FONT_TABLE_HEADER));
    cell.setBackgroundColor(GREY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    cell = new PdfPCell(new Phrase("Importo", FONT_TABLE_HEADER));
    cell.setBackgroundColor(GREY);
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell);

    BigDecimal total = new BigDecimal(0);

    for (int i = 0; i < allMsg.size(); i++) {
      final Msg msg = allMsg.get(i);
      final Color color = i % 2 == 0 ? Color.WHITE : GREY;
      total = total.add(msg.getAmount());

      final Gift gift = giftDao.getGiftFromId(msg.getIdGift());
      cell = new PdfPCell(new Phrase(gift.getTitle()));
      cell.setBackgroundColor(color);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(msg.getSender()));
      cell.setBackgroundColor(color);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(msg.getMsg()));
      cell.setBackgroundColor(color);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);

      cell = new PdfPCell(new Phrase(String.valueOf(msg.getAmount()) + " €"));
      cell.setBackgroundColor(color);
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(cell);
    }

    LOG.info("Totale incassato: {}", total);
    LOG.info("Media regalo: {}", total.divide(new BigDecimal(allMsg.size()), RoundingMode.HALF_UP));
    LOG.info("Numero di regali: {}" + allMsg.size());

    Paragraph main = new Paragraph();
    main.setAlignment(Element.ALIGN_CENTER);
    final Paragraph totale = new Paragraph("Totale incassato " + String.valueOf(total) + " €", FONT_SUBTITLE);
    main.add(totale);
    addEmptyLine(main, 1);
    final Paragraph media = new Paragraph("Media regalo " + total.divide(new BigDecimal(allMsg.size()), RoundingMode.HALF_UP) + " €", FONT_SUBTITLE);
    main.add(media);
    addEmptyLine(main, 1);
    final Paragraph count = new Paragraph("Numero di regali " + allMsg.size(), FONT_SUBTITLE);
    main.add(count);
    addEmptyLine(main, 2);
    document.add(main);

    document.add(table);
  }

  private void addTitle(final Document document) throws DocumentException {
    final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm:ss");

    final Date date = new Date();
    final String formattedDate = dateFormat.format(date);

    Paragraph main = new Paragraph();
    main.setAlignment(Element.ALIGN_CENTER);
    addEmptyLine(main, 3);
    final Paragraph paragraph = new Paragraph("Lista nozze aggiornata al " + formattedDate, FONT_TITLE);
    main.add(paragraph);
    addEmptyLine(main, 1);
    document.add(main);
  }

  private void addLogo(final Document document) {
    try {
      ClassLoader classLoader = getClass().getClassLoader();
      File file = new File(classLoader.getResource("logo.png").getFile());
      byte[] data = Files.toByteArray(file);
      final Image image = Image.getInstance(data);

      final float imageWidth = image.getWidth();
      final float imageHeight = image.getHeight();
      final float imageWidthScaled = LOGO_WIDTH;
      final float scaler = imageWidth / imageWidthScaled;
      final float imageHeightScaled = imageHeight / scaler;
      image.scaleAbsolute(imageWidthScaled, imageHeightScaled);


      final float pageHeight = PageSize.A4.rotate().getHeight();
      final float pageWidth = PageSize.A4.rotate().getWidth();
      final float absoluteX = pageWidth - document.rightMargin() - image.getScaledWidth();
      final float absoluteY = pageHeight - document.topMargin() - image.getScaledHeight();
      image.setAbsolutePosition(absoluteX, absoluteY);

      document.add(image);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }

}
