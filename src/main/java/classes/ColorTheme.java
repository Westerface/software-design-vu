package classes;

import java.awt.*;

public class ColorTheme {

    private Color backgroundColor;

    private Color headerBackgroundColor;
    private Color headerTextColor;
    private Color headerButtonBackgroundColor;
    private Color headerButtonHoverBackgroundColor;

    private Color dashboardButtonBackgroundColor;
    private Color dashboardButtonTextColor;
    private Color dashboardButtonBorderColor;
    private Color dashboardButtonHoverBackgroundColor;
    private Color dashboardButtonHoverTextColor;
    private Color dashboardButtonHoverBorderColor;

    private Color panelBackgroundColor;
    private Color panelTextColor;
    private Color panelItemSelectedColor;

    private Color optionsButtonBackgroundColor;
    private Color optionsButtonTextColor;
    private Color optionsButtonBorderColor;
    private Color optionsButtonHoverBackgroundColor;
    private Color optionsButtonHoverTextColor;
    private Color optionsButtonHoverBorderColor;

    private Color snippetScreenSplitBorderColor;

    private Color snippetScreenSaveButtonBackgroundColor;
    private Color snippetScreenSaveButtonTextColor;
    private Color snippetScreenSaveButtonHoverBackgroundColor;
    private Color snippetScreenSaveButtonHoverTextColor;

    public ColorTheme(){
        this.backgroundColor = new Color(240, 224, 229);
        this.headerBackgroundColor = new Color(72, 35, 46);
        this.headerTextColor = new Color(227, 211, 216);
        this.headerButtonBackgroundColor = new Color(72, 35, 46);
        this.headerButtonHoverBackgroundColor = new Color(198, 147, 178);
        this.dashboardButtonBackgroundColor = new Color(58, 30, 39);
        this.dashboardButtonTextColor = new Color(255, 255, 255);
        this.dashboardButtonBorderColor = new Color(72, 35, 46);
        this.dashboardButtonHoverBackgroundColor = new Color(98, 27, 57);
        this.dashboardButtonHoverTextColor = new Color(255, 255, 255);
        this.dashboardButtonHoverBorderColor = new Color(0,0,0);
        this.panelBackgroundColor = new Color(255, 255, 255);
        this.panelTextColor = new Color(0,0,0);
        this.panelItemSelectedColor = new Color(198, 147, 178);
        this.optionsButtonBackgroundColor = new Color(58, 30, 39);
        this.optionsButtonTextColor = new Color(255, 255, 255);
        this.optionsButtonBorderColor = new Color(72, 35, 46);
        this.optionsButtonHoverBackgroundColor = new Color(98, 27, 57);
        this.optionsButtonHoverTextColor = new Color(255, 255, 255);
        this.optionsButtonHoverBorderColor = new Color(0,0,0);
        this.snippetScreenSplitBorderColor = new Color(72, 35, 46);
        this.snippetScreenSaveButtonBackgroundColor = new Color(58, 30, 39);
        this.snippetScreenSaveButtonTextColor = new Color(255, 255, 255);
        this.snippetScreenSaveButtonHoverBackgroundColor = new Color(98, 27, 57);
        this.snippetScreenSaveButtonHoverTextColor = new Color(255, 255, 255);
    }
    public ColorTheme(Color backgroundColor,
                      Color headerBackgroundColor,
                      Color headerTextColor,
                      Color headerButtonBackgroundColor,
                      Color headerButtonHoverBackgroundColor,
                      Color dashboardButtonBackgroundColor,
                      Color dashboardButtonTextColor,
                      Color dashboardButtonBorderColor,
                      Color dashboardButtonHoverBackgroundColor,
                      Color dashboardButtonHoverTextColor,
                      Color dashboardButtonHoverBorderColor,
                      Color panelBackgroundColor,
                      Color panelTextColor,
                      Color panelItemSelectedColor,
                      Color optionsButtonBackgroundColor,
                      Color optionsButtonTextColor,
                      Color optionsButtonBorderColor,
                      Color optionsButtonHoverBackgroundColor,
                      Color optionsButtonHoverTextColor,
                      Color optionsButtonHoverBorderColor,
                      Color snippetScreenSplitBorderColor,
                      Color snippetScreenSaveButtonBackgroundColor,
                      Color snippetScreenSaveButtonTextColor,
                      Color snippetScreenSaveButtonHoverBackgroundColor,
                      Color snippetScreenSaveButtonHoverTextColor
    ) {
        this.backgroundColor = backgroundColor;
        this.headerBackgroundColor = headerBackgroundColor;
        this.headerTextColor = headerTextColor;
        this.headerButtonBackgroundColor = headerButtonBackgroundColor;
        this.headerButtonHoverBackgroundColor = headerButtonHoverBackgroundColor;
        this.dashboardButtonBackgroundColor = dashboardButtonBackgroundColor;
        this.dashboardButtonTextColor = dashboardButtonTextColor;
        this.dashboardButtonBorderColor = dashboardButtonBorderColor;
        this.dashboardButtonHoverBackgroundColor = dashboardButtonHoverBackgroundColor;
        this.dashboardButtonHoverTextColor = dashboardButtonHoverTextColor;
        this.dashboardButtonHoverBorderColor = dashboardButtonHoverBorderColor;
        this.panelBackgroundColor = panelBackgroundColor;
        this.panelTextColor = panelTextColor;
        this.panelItemSelectedColor = panelItemSelectedColor;
        this.optionsButtonBackgroundColor = optionsButtonBackgroundColor;
        this.optionsButtonTextColor = optionsButtonTextColor;
        this.optionsButtonBorderColor = optionsButtonBorderColor;
        this.optionsButtonHoverBackgroundColor = optionsButtonHoverBackgroundColor;
        this.optionsButtonHoverTextColor = optionsButtonHoverTextColor;
        this.optionsButtonHoverBorderColor = optionsButtonHoverBorderColor;
        this.snippetScreenSplitBorderColor = snippetScreenSplitBorderColor;
        this.snippetScreenSaveButtonBackgroundColor = snippetScreenSaveButtonBackgroundColor;
        this.snippetScreenSaveButtonTextColor = snippetScreenSaveButtonTextColor;
        this.snippetScreenSaveButtonHoverBackgroundColor = snippetScreenSaveButtonHoverBackgroundColor;
        this.snippetScreenSaveButtonHoverTextColor = snippetScreenSaveButtonHoverTextColor;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(Color headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public Color getHeaderTextColor() {
        return headerTextColor;
    }

    public void setHeaderTextColor(Color headerTextColor) {
        this.headerTextColor = headerTextColor;
    }

    public Color getHeaderButtonBackgroundColor() {
        return headerButtonBackgroundColor;
    }

    public void setHeaderButtonBackgroundColor(Color headerButtonBackgroundColor) {
        this.headerButtonBackgroundColor = headerButtonBackgroundColor;
    }

    public Color getHeaderButtonHoverBackgroundColor() {
        return headerButtonHoverBackgroundColor;
    }

    public void setHeaderButtonHoverBackgroundColor(Color headerButtonHoverBackgroundColor) {
        this.headerButtonHoverBackgroundColor = headerButtonHoverBackgroundColor;
    }

    public Color getDashboardButtonBackgroundColor() {
        return dashboardButtonBackgroundColor;
    }

    public void setDashboardButtonBackgroundColor(Color dashboardButtonBackgroundColor) {
        this.dashboardButtonBackgroundColor = dashboardButtonBackgroundColor;
    }

    public Color getDashboardButtonTextColor() {
        return dashboardButtonTextColor;
    }

    public void setDashboardButtonTextColor(Color dashboardButtonTextColor) {
        this.dashboardButtonTextColor = dashboardButtonTextColor;
    }

    public Color getDashboardButtonBorderColor() {
        return dashboardButtonBorderColor;
    }

    public void setDashboardButtonBorderColor(Color dashboardButtonBorderColor) {
        this.dashboardButtonBorderColor = dashboardButtonBorderColor;
    }

    public Color getDashboardButtonHoverBackgroundColor() {
        return dashboardButtonHoverBackgroundColor;
    }

    public void setDashboardButtonHoverBackgroundColor(Color dashboardButtonHoverBackgroundColor) {
        this.dashboardButtonHoverBackgroundColor = dashboardButtonHoverBackgroundColor;
    }

    public Color getDashboardButtonHoverTextColor() {
        return dashboardButtonHoverTextColor;
    }

    public void setDashboardButtonHoverTextColor(Color dashboardButtonHoverTextColor) {
        this.dashboardButtonHoverTextColor = dashboardButtonHoverTextColor;
    }

    public Color getDashboardButtonHoverBorderColor() {
        return dashboardButtonHoverBorderColor;
    }

    public void setDashboardButtonHoverBorderColor(Color dashboardButtonHoverBorderColor) {
        this.dashboardButtonHoverBorderColor = dashboardButtonHoverBorderColor;
    }

    public Color getPanelBackgroundColor() {
        return panelBackgroundColor;
    }

    public void setPanelBackgroundColor(Color panelBackgroundColor) {
        this.panelBackgroundColor = panelBackgroundColor;
    }

    public Color getPanelTextColor() {
        return panelTextColor;
    }

    public void setPanelTextColor(Color panelTextColor) {
        this.panelTextColor = panelTextColor;
    }

    public Color getPanelItemSelectedColor() {
        return panelItemSelectedColor;
    }

    public void setPanelItemSelectedColor(Color panelItemSelectedColor) {
        this.panelItemSelectedColor = panelItemSelectedColor;
    }

    public Color getOptionsButtonBackgroundColor() {
        return optionsButtonBackgroundColor;
    }

    public void setOptionsButtonBackgroundColor(Color optionsButtonBackgroundColor) {
        this.optionsButtonBackgroundColor = optionsButtonBackgroundColor;
    }

    public Color getOptionsButtonTextColor() {
        return optionsButtonTextColor;
    }

    public void setOptionsButtonTextColor(Color optionsButtonTextColor) {
        this.optionsButtonTextColor = optionsButtonTextColor;
    }

    public Color getOptionsButtonBorderColor() {
        return optionsButtonBorderColor;
    }

    public void setOptionsButtonBorderColor(Color optionsButtonBorderColor) {
        this.optionsButtonBorderColor = optionsButtonBorderColor;
    }

    public Color getOptionsButtonHoverBackgroundColor() {
        return optionsButtonHoverBackgroundColor;
    }

    public void setOptionsButtonHoverBackgroundColor(Color optionsButtonHoverBackgroundColor) {
        this.optionsButtonHoverBackgroundColor = optionsButtonHoverBackgroundColor;
    }

    public Color getOptionsButtonHoverTextColor() {
        return optionsButtonHoverTextColor;
    }

    public void setOptionsButtonHoverTextColor(Color optionsButtonHoverTextColor) {
        this.optionsButtonHoverTextColor = optionsButtonHoverTextColor;
    }

    public Color getOptionsButtonHoverBorderColor() {
        return optionsButtonHoverBorderColor;
    }

    public void setOptionsButtonHoverBorderColor(Color optionsButtonHoverBorderColor) {
        this.optionsButtonHoverBorderColor = optionsButtonHoverBorderColor;
    }

    public Color getSnippetScreenSplitBorderColor() {
        return snippetScreenSplitBorderColor;
    }

    public void setSnippetScreenSplitBorderColor(Color snippetScreenSplitBorderColor) {
        this.snippetScreenSplitBorderColor = snippetScreenSplitBorderColor;
    }

    public Color getSnippetScreenSaveButtonBackgroundColor() {
        return snippetScreenSaveButtonBackgroundColor;
    }

    public void setSnippetScreenSaveButtonBackgroundColor(Color snippetScreenSaveButtonBackgroundColor) {
        this.snippetScreenSaveButtonBackgroundColor = snippetScreenSaveButtonBackgroundColor;
    }

    public Color getSnippetScreenSaveButtonTextColor() {
        return snippetScreenSaveButtonTextColor;
    }

    public void setSnippetScreenSaveButtonTextColor(Color snippetScreenSaveButtonTextColor) {
        this.snippetScreenSaveButtonTextColor = snippetScreenSaveButtonTextColor;
    }

    public Color getSnippetScreenSaveButtonHoverBackgroundColor() {
        return snippetScreenSaveButtonHoverBackgroundColor;
    }

    public void setSnippetScreenSaveButtonHoverBackgroundColor(Color snippetScreenSaveButtonHoverBackgroundColor) {
        this.snippetScreenSaveButtonHoverBackgroundColor = snippetScreenSaveButtonHoverBackgroundColor;
    }

    public Color getSnippetScreenSaveButtonHoverTextColor() {
        return snippetScreenSaveButtonHoverTextColor;
    }

    public void setSnippetScreenSaveButtonHoverTextColor(Color snippetScreenSaveButtonHoverTextColor) {
        this.snippetScreenSaveButtonHoverTextColor = snippetScreenSaveButtonHoverTextColor;
    }
}
