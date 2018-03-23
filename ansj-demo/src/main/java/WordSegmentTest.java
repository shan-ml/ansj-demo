import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.nlpcn.commons.lang.tire.domain.Forest;
import org.nlpcn.commons.lang.tire.library.Library;

import java.util.List;

/**
 * anjs自定义字典文件测试
 * 测试文字：这是一段测试文字
 * 未使用自定义字典分词结果：这:r   是:v   一段:m   测试:vn   文字:n
 * 使用了自定义字典将‘这是’当作一个名词配置在文件中之后的分词结果：这是:n   一段:m   测试:vn   文字:n
 */
public class WordSegmentTest {

    public static void main(String[] args) {
        String text = "这是一段测试文字";
        List<Term> termList = segWord(text);
        for (Term term : termList) {
            System.out.println(term.getName() + ":" + term.getNatureStr());
        }
    }

    /**
     * ansj分词
     *
     * @param text
     * @return
     */
    public static List<Term> segWord(String text) {

        Forest forest = null;
        try {
            //加载自定义字典文件
            forest = Library.makeForest(WordSegmentTest.class.getResourceAsStream("/library/userLibrary.dic"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用anjs精准分词模式
        Result result = ToAnalysis.parse(text, forest);
        List<Term> termList = result.getTerms();
        return termList;

    }
}