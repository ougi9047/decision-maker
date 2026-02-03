# 論理的意思決定サポーター (Decision Maker)

「迷いを数値化し、客観的な判断を支援する」フルスタックWebアプリケーションです。

##  プロジェクトの概要
私自身の課題である決断力を克服するために客観的な数値に基づいてサポートをするwebアプリ開発しました。
自分で設定した評価項目と、その重要度（重み）を掛け合わせることで、納得感のある結論を導き出します。

##  技術スタック
- **Backend:** Java 21 / Spring Boot 3.4 / Spring Data JPA / H2 Database
- **Frontend:** JavaScript (Vanilla) / Chart.js / Bootstrap 5
- **Build Tool:** Maven

##  主な機能
- **動的な評価項目の管理:** 比較項目の追加・削除、および「重み」の設定が可能。
- **候補対象の管理:** 複数の比較対象をリスト管理し、名称の変更も柔軟に対応。
- **レーダーチャートによる視覚化:** Chart.js を用い、各候補の強み・弱みを一目で把握。
- **独自の計算ロジック:** `スコア × 重み` の総和をバックエンド側で計算し、リアルタイムでランキングを算出。
- **データのリセット機能:** 枠組みを維持したまま、評価のみをやり直す「一括削除」機能を搭載。

##  開発における課題解決（アピールポイント）
- **JSONの無限ループ問題の解消:** `Decision` と `Criterion` の双方向参照による循環参照エラーを、Jacksonアノテーション（`@JsonManagedReference`, `@JsonBackReference`）を適切にペアリングすることで解決しました。
- **直感的なユーザー体験:** 数値入力と連動してグラフが描画される仕組みを構築し、ストレスのない操作感を実現しました。

##  実行方法
```bash
git clone [https://github.com/ougi9047/decision-maker.git](https://github.com/ougi9047/decision-maker.git)
mvn spring-boot:run
# ブラウザで http://localhost:8080 を開く

<img width="1104" height="1079" alt="image" src="https://github.com/user-attachments/assets/4349a592-56d5-481b-99a4-efc1ebdb4fb9" />
