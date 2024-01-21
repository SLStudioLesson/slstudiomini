# SLStudio Spring Boot Course
これはSLスタジオ(https://sls.ideal-growth.jp/)の学習サポートプロジェクトです。

# 対象レッスン
Spring Bootコース
- Chapter03 - Lesson01

このプロジェクトはSLスタジオ Spring Bootコース「SLStudio Mini」のサンプルソースコードです。

# GithubやGit操作に慣れていない人

Git操作に慣れていない人は、画面上の「Code」のボタンから「Download ZIP」を選択しましょう。
プロジェクトがダウンロード出来たら

**src->main**
以下を学習中のSLStudioMiniプロジェクトに全て上書きして下さい.

# プロジェクトの補足

## spring.profiles.active=dev
開発用処理に切り替えている為、ダミーログインを行っています。
ダミーログインユーザーの切替はdev/DevAuthenticationFilter.javaを編集して下さい。
通常のログインに戻したい時はdev以外の値を設定するかこの記述自体をコメント又は削除して下さい。
