package it.unitn.disi.wp.progetto.servlets;

import it.unitn.disi.wp.progetto.persistence.dao.RicettaDAO;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOException;
import it.unitn.disi.wp.progetto.persistence.dao.exceptions.DAOFactoryException;
import it.unitn.disi.wp.progetto.persistence.dao.factories.DAOFactory;
import it.unitn.disi.wp.progetto.persistence.entities.ElemReportNazionale;
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
import java.util.List;

@WebServlet(name = "XLSReportNazServlet", urlPatterns = {"/docs/reportnazionale"})
public class XLSReportNazServlet extends HttpServlet {

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("idrovincia");
        List<ElemReportNazionale> listReport = null;

        //ho letteralmente copiato e incollato le seguenti 3 righe da PDFRicevutaSerlvet, dato che è l'unica parte di codice che mi sembra sia utile al mio fine
        //e prima che tu mi chieda se l'ho testato, la risposta è no, perchè devo mettere a cuocere i funghi
        //e probabilmente tutto ciò che c'è dalla riga 83 in poi non credo serva perchè è per scriverlo lato server ma io non lo tolgo
        response.setContentType("application/xls");
        String excelFilePath = "ReportNazionale.xls";
        response.addHeader("Content-Disposition", "inline; filename=" + excelFilePath);

        try {
            listReport = ricettaDAO.reportNazionale(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        int rowCount = 0;

        for (ElemReportNazionale report : listReport) {
            Row row = sheet.createRow(++rowCount);

            Cell cell = row.createCell(1);
            cell.setCellValue(report.getProvincia());

            cell = row.createCell(2);
            cell.setCellValue(report.getCfMedico());

            cell = row.createCell(3);
            cell.setCellValue(report.getSpesa());

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
