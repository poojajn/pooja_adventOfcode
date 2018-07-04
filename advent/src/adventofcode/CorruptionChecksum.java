package adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CorruptionChecksum {

	 public interface RowChecksumCalculator {

	        int calculateRowChecksum(List<Integer> row);

	    }


	    private List<List<Integer>> spreadsheet;


	    private CorruptionChecksum(List<List<Integer>> spreadsheet) {
	        this.spreadsheet = spreadsheet;
	    }

	    public static CorruptionChecksum parseSpreadsheet(String spreadsheetStr) {
	        String[] splittedRows = spreadsheetStr.split("\n");
	        List<List<Integer>> spreadsheet = new ArrayList<>(splittedRows.length);

	        for (int rowIndex = 0; rowIndex < splittedRows.length; rowIndex++) {
	            String row = splittedRows[rowIndex];
	            String[] splittedColumns = row.split("\\s+");
	            List<Integer> columns = new ArrayList<>(splittedColumns.length);
	            spreadsheet.add(rowIndex, columns);

	            for (int columnIndex = 0; columnIndex < splittedColumns.length; columnIndex++) {
	                String fieldValue = splittedColumns[columnIndex];
	                columns.add(columnIndex, Integer.valueOf(fieldValue));
	            }
	        }
	        return new CorruptionChecksum(spreadsheet);
	    }


	    public int calculateChecksum(RowChecksumCalculator rowChecksumCalculator) {
	        int checksum = 0;
	        for (int rowIndex = 0; rowIndex < spreadsheet.size(); rowIndex++) {
	            List<Integer> row = spreadsheet.get(rowIndex);
	            int rowChecksum = rowChecksumCalculator.calculateRowChecksum(row);
	            checksum += rowChecksum;
	        }
	        return checksum;
	    }


	    @Override
	    public String toString() {
	        StringBuilder sb = new StringBuilder();
	        for (int rowIndex = 0; rowIndex < spreadsheet.size(); rowIndex++) {
	            List<Integer> rows = spreadsheet.get(rowIndex);
	            for (int columnIndex = 0; columnIndex < rows.size(); columnIndex++) {
	                Integer fieldValue = rows.get(columnIndex);
	                sb.append(String.format("%6d ", fieldValue));
	            }
	            sb.setCharAt(sb.length() - 1, '\n');
	        }
	        return sb.toString();
	    }


	    public static RowChecksumCalculator differenceBetweenMinAndMaxValueChecksum() {
	        return row -> {
	            int minValue = Integer.MAX_VALUE;
	            int maxValue = Integer.MIN_VALUE;

	            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
	                int fieldValue = row.get(columnIndex);
	                if (fieldValue < minValue) {
	                    minValue = fieldValue;
	                }
	                if (fieldValue > maxValue) {
	                    maxValue = fieldValue;
	                }
	            }
	            int rowChecksum = maxValue - minValue;
	            return rowChecksum;
	        };
	    }

	    public static RowChecksumCalculator evenDivisionChecksum() {
	        return row -> {
	            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
	                int fieldValue = row.get(columnIndex);
	                for (int i = 0; i < row.size(); i++) {
	                    int otherFieldValue = row.get(i);
	                    boolean dividesEvenly = fieldValue % otherFieldValue == 0;
	                    if (dividesEvenly && i != columnIndex) {
	                        return fieldValue / otherFieldValue;
	                    }
	                }
	            }
	            return 0;
	        };
	    }


	    public static void main(String[] args) {
	        if (args.length == 0) {
	            System.out.println("Empty args[] without any spreadsheet's to check");
	            return;
	        }

	        for (String spreadsheet : args) {
	            try {
	                CorruptionChecksum corruptionChecksum = CorruptionChecksum.parseSpreadsheet(spreadsheet);
	                System.out.println(spreadsheet);

	                int checksumPart1 = corruptionChecksum.calculateChecksum(differenceBetweenMinAndMaxValueChecksum());
	                System.out.println("checksum part 1: " + checksumPart1);

	                int checksumPart2 = corruptionChecksum.calculateChecksum(evenDivisionChecksum());
	                System.out.println("checksum part 2: " + checksumPart2);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }

}
