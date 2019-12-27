package it.unitn.disi.wp.progetto.servlets;

import it.unitn.disi.wp.progetto.persistence.dao.EsamePrescrittoDAO;
import it.unitn.disi.wp.progetto.persistence.dao.RicettaDAO;
import it.unitn.disi.wp.progetto.persistence.dao.VisitaMedicoSpecialistaDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import it.unitn.disi.wp.progetto.persistence.dao.factories.DAOFactory;
import it.unitn.disi.wp.progetto.persistence.entities.ElemReportProv;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "XLSReportProvServlet", urlPatterns = {"/docs/reportprov"})
public class XLSReportProvServlet extends HttpServlet {

    private final String RICETTA = "ricetta";

    private RicettaDAO ricettaDAO;

    @Override
    public void init() throws ServletException {
        DAOFactory daoFactory = (DAOFactory) super.getServletContext().getAttribute("daoFactory");
        if (daoFactory != null) {
            try {
                ricettaDAO = daoFactory.getDAO(RicettaDAO.class);
            } catch (DAOFactoryException ex) {
                throw new ServletException("Impossible to get dao factory for storage system");
            }
        }
        else {
            throw new ServletException("Impossible to get dao factory for storage system");
        }
    }
/*
    public static void main(String[] args) {

        //creo una lista di libri
        Book book1 = new Book("Head First Java", "Kathy Serria", 79);
        Book book2 = new Book("Effective Java", "Joshua Bloch", 36);
        Book book3 = new Book("Clean Code", "Robert Martin", 42);
        Book book4 = new Book("Thinking in Java", "Bruce Eckel", 35);

        List<Book> listBook = Arrays.asList(book1, book2, book3, book4);

        String excelFilePath = "NiceJavaBooks.xls";

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (Book aBook : listBook) {
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(1);
            cell.setCellValue(aBook.getTitle());

            cell = row.createCell(2);
            cell.setCellValue(aBook.getAuthor());

            cell = row.createCell(3);
            cell.setCellValue(aBook.getPrice());
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)){
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }
 */

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("idrovincia");
        List<ElemReportProv> listReport = null;

        //ho letteralmente copiato e incollato le seguenti 3 righe da PDFRicevutaSerlvet, dato che è l'unica parte di codice che mi sembra sia utile al mio fine
        //e prima che tu mi chieda se l'ho testato, la risposta è no, perchè devo mettere a cuocere i funghi
        //e probabilmente tutto ciò che c'è dalla riga 136 in poi non credo serva perchè è per scriverlo lato server ma io non lo tolgo
        response.setContentType("application/xls");
        String excelFilePath = "ReportProvinciale.xls";
        response.addHeader("Content-Disposition", "inline; filename=" + excelFilePath);

        try {
             listReport = ricettaDAO.reportProvinciale(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }


        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (ElemReportProv report : listReport) {
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(1);
            cell.setCellValue(report.getEmissione());

            cell = row.createCell(2);
            cell.setCellValue(report.getFarmaco());

            cell = row.createCell(3);
            cell.setCellValue(report.getFarmacia());

            cell = row.createCell(4);
            cell.setCellValue(report.getCfMedico());

            cell = row.createCell(5);
            cell.setCellValue(report.getCfPaziente());

            cell = row.createCell(6);
            cell.setCellValue(report.getPrezzo());
        }

        try (FileOutputStream outputStream = new FileOutputStream(excelFilePath)){
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }

}
