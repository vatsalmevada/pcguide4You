package com.pcguide4you.commonsCLI;

import org.apache.commons.cli.*;

/**
 * @author vatsal mevada (mevadavatsal[at]gmail[dot]com)
 */
public class Calculator {

    private static final String OPERAND_1 = "o1";
    private static final String OPERAND_2 = "o2";
    private static final String OPERATOR = "op";

    public static void main(String[] args) {
        Options options = getCLIOptions();
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            float operand1 = Float.parseFloat(cmd.getOptionValue(OPERAND_1));
            float operand2 = Float.parseFloat(cmd.getOptionValue(OPERAND_2));
            String operator = cmd.getOptionValue(OPERATOR);
            System.out.println("Result: " + calculate(operator, operand1, operand2));
        } catch (ParseException | IllegalArgumentException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("calculator-<version>.jar", options);
        }
    }

    //build command line arguments
    private static Options getCLIOptions() {
        Option operand1 = Option.builder(OPERAND_1).desc("First operand: Must be a number").hasArg()
                .required().type(Number.class).longOpt("first-operand").build();
        Option operand2 = Option.builder(OPERAND_2).desc("Second operand: Must be a number").hasArg()
                .required().type(Number.class).longOpt("second-operand").build();
        Option operator = Option.builder(OPERATOR).desc("operator: must be one of these -> +,-,*,/").hasArg()
                .required().type(Character.class).longOpt("operator").build();
        return new Options().addOption(operand1).addOption(operand2).addOption(operator);
    }

    private static float calculate(String operator, float operand1, float operand2) {
        float result;
        switch (operator) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return result;
    }
}
