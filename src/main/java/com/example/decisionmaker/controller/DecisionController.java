package com.example.decisionmaker.controller;

import com.example.decisionmaker.entity.Criterion;
import com.example.decisionmaker.entity.Decision;
import com.example.decisionmaker.entity.Evaluation;
import com.example.decisionmaker.repository.DecisionRepository;
import com.example.decisionmaker.service.DecisionService; // 追加を忘れずに！
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/decisions")
public class DecisionController {

    @Autowired
    private DecisionRepository decisionRepository;

    @Autowired
    private DecisionService decisionService;

    // すべての意思決定データを取得する
    @GetMapping
    public List<Decision> getAllDecisions() {
        return decisionRepository.findAll();
    }

    // 特定の意思決定の計算結果（スコア）を取得する
    @GetMapping("/{id}/result")

    public Map<String, Object> getResult(@PathVariable Long id) {
        Decision decision = decisionRepository.findById(id).orElseThrow();
        return decisionService.calculateDetailedResult(decision);
    }

    @GetMapping("/{id}")
    public Decision getDecision(@PathVariable Long id) {
        return decisionRepository.findById(id).orElseThrow();
    }

    // 新しい意思決定を保存する
    @PostMapping
    public Decision createDecision(@RequestBody Decision decision) {
        return decisionRepository.save(decision);
    }

    @Autowired
    private com.example.decisionmaker.repository.EvaluationRepository evaluationRepository;

    @PostMapping("/evaluations")
    public void saveEvaluations(@RequestBody List<Map<String, Object>> payload) {
        for (Map<String, Object> item : payload) {
            Long alternativeId = Long.valueOf(item.get("alternativeId").toString());
            Long criterionId = Long.valueOf(item.get("criterionId").toString());
            Integer score = Integer.valueOf(item.get("score").toString());

            // 既存の評価データがあるか探す
            Evaluation evaluation = evaluationRepository
                    .findByAlternativeIdAndCriterionId(alternativeId, criterionId)
                    .orElse(new Evaluation()); // なければ新規作成

            // データをセット
            evaluation.setScore(score);
            evaluation.setAlternative(new com.example.decisionmaker.entity.Alternative());
            evaluation.getAlternative().setId(alternativeId);
            evaluation.setCriterion(new com.example.decisionmaker.entity.Criterion());
            evaluation.getCriterion().setId(criterionId);

            evaluationRepository.save(evaluation);
        }
    }

    @Autowired
    private com.example.decisionmaker.repository.CriterionRepository criterionRepository;

    // 項目を追加するAPI
    @PostMapping("/{id}/criteria")
    public void addCriterion(@PathVariable Long id, @RequestBody Criterion criterion) {
        Decision decision = decisionRepository.findById(id).orElseThrow();
        criterion.setDecision(decision);
        criterionRepository.save(criterion);
    }

    // 項目を削除するAPI
    @DeleteMapping("/criteria/{criterionId}")
    public void deleteCriterion(@PathVariable Long criterionId) {
        criterionRepository.deleteById(criterionId);
    }

    @Autowired
    private com.example.decisionmaker.repository.AlternativeRepository alternativeRepository;

    //  評価対象（会社など）を追加する
    @PostMapping("/{id}/alternatives")
    public void addAlternative(@PathVariable Long id, @RequestBody com.example.decisionmaker.entity.Alternative alternative) {
        Decision decision = decisionRepository.findById(id).orElseThrow();
        alternative.setDecision(decision);
        alternativeRepository.save(alternative);
    }

    //  評価対象を削除する
    @DeleteMapping("/alternatives/{altId}")
    public void deleteAlternative(@PathVariable Long altId) {
        alternativeRepository.deleteById(altId);
    }

    //  評価対象の名前を変更する
    @PutMapping("/alternatives/{altId}")
    public void updateAlternativeName(@PathVariable Long altId, @RequestBody Map<String, String> body) {
        com.example.decisionmaker.entity.Alternative alt = alternativeRepository.findById(altId).orElseThrow();
        alt.setName(body.get("name"));
        alternativeRepository.save(alt);
    }

    // スコアをすべてリセットするAPI
    @DeleteMapping("/{id}/evaluations")
    public void resetEvaluations(@PathVariable Long id) {
        // このDecisionに紐づくEvaluation（点数）だけをすべて削除
        evaluationRepository.deleteByCriterionDecisionId(id);
    }
    // 分析対象（Decision）のタイトルを変更するAPI
    @PutMapping("/{id}")
    public void updateDecision(@PathVariable Long id, @RequestBody Map<String, String> body) {
        Decision decision = decisionRepository.findById(id).orElseThrow();
        // 送られてきたJSONの "title" をセットする
        decision.setTitle(body.get("title"));
        decisionRepository.save(decision);
    }
}