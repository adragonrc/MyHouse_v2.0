package com.alquilerapp.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class PDF {
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph paragraph;
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.NORMAL);
    private Font fSuvtitle = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.NORMAL);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);
    private Font fHint = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.NORMAL);

    public  void openDocument(){
        createFile();
        try {
            document = new Document(PageSize.A4);
            pdfWriter = PdfWriter.getInstance(document, new FileOutputStream((pdfFile)));
            document.open();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createFile(){
        File folder = new File(Environment.getExternalStorageDirectory().toString(), "PDF");
        if (!folder.exists())   folder.mkdir();

        pdfFile = new File(folder, "TemplatePDF.pdf");
    }

    public void closeDocument(){
        document.close();
    }

    public  void addMetaData(String title, String subject, String autor){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(autor);
    }
    public  void addTitles(String title, String subTitle, String date){
        try {
            paragraph = new Paragraph();
            addChild(new Paragraph(title, fTitle));
            addChild(new Paragraph(subTitle, fSuvtitle));
            addChild(new Paragraph(date, fHint));
            paragraph.setSpacingAfter(30);
            document.add(paragraph);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void addChild(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);

    }
    public void addParagraph(String text){
        try {
            paragraph = new Paragraph();
            addChild(new Paragraph(text, fText));
            paragraph.setSpacingAfter(5);
            paragraph.setSpacingBefore(5);
            document.add(paragraph);

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
    public void createTable(String []header, ArrayList<String[]>clients){
        try {
            paragraph = new Paragraph();
            paragraph.setFont(fText);
            PdfPTable pdfPTable = new PdfPTable(header.length);
            pdfPTable.setWidthPercentage(100);
            PdfPCell pdfPCell;
            int indexC = 0;
            while (indexC<header.length){
                pdfPCell = new PdfPCell(new Phrase(header[indexC++ ], fSuvtitle));
                pdfPCell.setBackgroundColor(BaseColor.GREEN);
                pdfPCell.setBorder(10);
                pdfPTable.addCell(pdfPCell);
            }
            for (int indexR = 0 ; indexR<clients.size(); indexR++){
                String[] row = clients.get(indexR);
                for (indexC = 0 ; indexC<header.length; indexC++) {
                    pdfPCell = new PdfPCell(new Phrase(row[indexC]));
                    pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    pdfPCell.setFixedHeight(40);
                    pdfPCell.setBorder(0);
                    pdfPTable.addCell(pdfPCell);
                }
            }
            paragraph.add(pdfPTable);
            document.add(pdfPTable);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    public File getPdfFile() {
        return pdfFile;
    }
}
