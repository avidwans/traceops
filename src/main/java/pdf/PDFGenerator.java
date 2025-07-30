package pdf;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import model.ExceptionAnalysis;

import java.awt.*;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {
    public static void generate(List<ExceptionAnalysis> list, String outputPath) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(outputPath));
        doc.open();

        Font headerFont = new Font(Font.HELVETICA, 14, Font.BOLD);
        Font normalFont = new Font(Font.HELVETICA, 12);
        Color pastelBlue = new Color(204, 229, 255);  // Updated here

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);

        String[] headers = {"Exception", "Number of occurrence", "Root Cause", "Possible Solutions"};
        for (String h : headers) {
            PdfPCell cell = new PdfPCell(new Phrase(h, headerFont));
            cell.setBackgroundColor(pastelBlue);
            table.addCell(cell);
        }

        for (ExceptionAnalysis ea : list) {
            table.addCell(new Phrase(ea.getException(), normalFont));
            table.addCell(new Phrase(String.valueOf(ea.getOccurrence()), normalFont));
            table.addCell(new Phrase(ea.getRootCause(), normalFont));
            table.addCell(new Phrase(ea.getPossibleSolutions(), normalFont));
        }

        doc.add(table);
        doc.close();
    }
}
