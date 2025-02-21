package com.study.board.demo.utils;

/**
 * 
 * <PRE>
 * Comment  :  페이징 처리를 위한 페이징계산 /
 *
 * History  : 2020.05.27 - 최초작성
 * </PRE>
 * @version : 1.0
 *
 */
public class Paging {
	   /**
     * 페이징을 쓰고있는 현제 페이지명
     */
    protected String sURL;

    /**
     * 총 레코드수
     */
    protected int nTotalRows;

    /**
     * 페이지당 레코드 갯수
     */
    protected int nPageSize;

    /**
     * 각 페이지당 보여줄 페이지 블록의 개수 (예: << < 1 2 3 4 5 > >> 에서의 숫자부분의 갯수)
     */
    protected int nBlockSize;

    /**
     * 현재 페이지
     */
    protected int nCurrentPage;

    /**
     * 첫페이지의로의 링크가 필요한 지의 여부
     */
    protected boolean needHeadPageAction;

    /**
     * 마지막 페이지로의 링크가 필요한 지의 여부
     */
    protected boolean needEndPageAction;

    /**
     * 이전 블럭 링크가 필요한 지의 여부
     */
    protected boolean needPrevBlockAction;

    /**
     * 이전 페이지 링크가 필요한 지의 여부
     */
    protected boolean needPrevPageAction;

    /**
     * 다음 페이지 링크가 필요한 지의 여부
     */
    protected boolean needNextPageAction;

    /**
     * 다음 블럭 링크가 필요한 지의 여부
     */
    protected boolean needNextBlockAction;

    /**
     * 현재 블럭
     */
    protected int nCurrentBlock;

    /**
     * 총페이지 수
     */
    protected int nTotalPages;

    /**
     * 총블럭 수
     */
    protected int nTotalBlocks;

    /**
     * 현재 블럭에서 최초로 표시되는 페이지
     */
    protected int nStartPage;

    /**
     * 생성자
     *
     * @param   url             페이징이 적용될 페이지 URL
     * @param   totalRows       페이징을 적용할 데이터들의 총 열갯수
     */
    public Paging(String url, int totalRows) {
        sURL = url;
        nTotalRows = totalRows;
        nPageSize = 10;
        nBlockSize = 10;
        nCurrentPage = 1;

        calculate();
    }

    /**
     * 생성자
     *
     * @param   url             페이징이 적용될 페이지 URL
     * @param   totalRows       페이징을 적용할 데이터들의 총 열갯수
     * @param   currentPage     현재 페이지
     */
    public Paging(String url, int totalRows, int currentPage) {
        sURL = url;
        nTotalRows = totalRows;
        nPageSize = 10;
        nBlockSize = 10;
        nCurrentPage = currentPage;

        calculate();
    }

    /**
     * 생성자
     *
     * @param   url             페이징이 적용될 페이지 URL
     * @param   totalRows       페이징을 적용할 데이터들의 총 열갯수
     * @param   pageSize        각 페이지당 보여줄 열갯수
     * @param   blockSize       각 페이지당 보여줄 페이지 블록의 개수 (예: << < 1 2 3 4 5 > >> 에서의 숫자부분의 갯수)
     * @param   currentPage     현재 페이지
     */
    public Paging(String url, int totalRows, int currentPage, int pageSize, int blockSize) {
        sURL = url;
        nTotalRows = totalRows;
        nPageSize = pageSize;
        nBlockSize = blockSize;
        nCurrentPage = currentPage;

        calculate();
    }

    /**
     * 생성자의 입력값으로부터 필요한 값들을 계산해낸다.
     */
    private void calculate() {
        nTotalPages = (int)((nTotalRows - 1) / nPageSize) + 1;
        nTotalBlocks = (int)((nTotalPages - 1) / nBlockSize) + 1;
        nCurrentBlock = (int)((nCurrentPage - 1) / nBlockSize) + 1;

        needHeadPageAction = (nCurrentPage > 1);
        needEndPageAction = (nCurrentPage < nTotalPages);
        needPrevBlockAction = (nCurrentBlock > 1);
        needNextBlockAction = (nCurrentBlock < nTotalBlocks);
        needPrevPageAction = (nCurrentPage > 1);
        needNextPageAction = (nCurrentPage < nTotalPages);
        nStartPage = (nCurrentBlock - 1) * nBlockSize + 1;
    }

 
    /**
     * 페이지가 변경될때의 새로운 URL을 반환한다.
     *
     * @param   page     redirect하고 싶은 페이지 번호
     * @return  redirect해야할 url
     */
    protected String getReturnURL(int page) {		
    	int start = sURL.indexOf("page=");
    	if (start > -1) {
        	String sBlock = null;
    		int end = sURL.indexOf("&", start);
    		if (end > -1) sBlock = sURL.substring(start, end);
    		else sBlock = sURL.substring(start);
    		
    		sURL = sURL.replace(sBlock, "");
    		sURL = sURL.replace("&&", "&");
    		sURL = sURL.replace("?&", "?");
    	}

    	if (sURL.indexOf("?") > -1) return sURL + "&page=" + page;
        else return sURL + "?page=" + page;   
    }

    public int getTotalPages() {
        return nTotalPages;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("<ul class='pagination'>");
        if(nCurrentPage > 1){
            buf.append("<li class='disabled'><a href='#'>«</a></li>");
        }

        for (int i=0; i<nBlockSize; i++) {
            String sHtml = "";
            int n = i + nStartPage;

            if (n <= nTotalPages) {
                sHtml =   "<li " + ((n == nCurrentPage) ? "class='active'>" : ">")
                        + "<a href=\"" + pageURL(n) + "\">" + n + "</a>\n"
                        + "</li>";

                buf.append(sHtml);
            }
            else break;
        }

        if(nCurrentPage < nTotalPages){
            buf.append("<li><a href='#'>»</a></li>");
        }
        buf.append("</ul>");
        return buf.toString();
    }

    private String pageURL(int page){
        String pageURL = sURL;
        if (sURL.contains("javascript")){
            pageURL = pageURL.replace("[page]", String.valueOf(page));
        }else{
            pageURL = getReturnURL(page);
        }
        pageURL = pageURL.replace("&&", "&");
        pageURL = pageURL.replace("/&/g", "%26");

        return pageURL;
    }

    public String getsURL() {
        return sURL;
    }

    public int getnTotalRows() {
        return nTotalRows;
    }

    public int getnPageSize() {
        return nPageSize;
    }

    public int getnBlockSize() {
        return nBlockSize;
    }

    public int getnCurrentPage() {
        return nCurrentPage;
    }

    public boolean isNeedHeadPageAction() {
        return needHeadPageAction;
    }

    public boolean isNeedEndPageAction() {
        return needEndPageAction;
    }

    public boolean isNeedPrevBlockAction() {
        return needPrevBlockAction;
    }

    public boolean isNeedPrevPageAction() {
        return needPrevPageAction;
    }

    public boolean isNeedNextPageAction() {
        return needNextPageAction;
    }

    public boolean isNeedNextBlockAction() {
        return needNextBlockAction;
    }

    public int getnCurrentBlock() {
        return nCurrentBlock;
    }

    public int getnTotalPages() {
        return nTotalPages;
    }

    public int getnTotalBlocks() {
        return nTotalBlocks;
    }

    public int getnStartPage() {
        return nStartPage;
    }
}
