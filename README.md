# よくあるエラーと対処法

Spring Boot + Thymeleaf を使った開発でつまずきやすいエラーをまとめています。

---

## 1. ポート 8080 が使用中でサーバーを起動できない

別のプロセスがポート 8080 を占有していると、Spring Boot の起動に失敗します。  
VS Code のターミナルまたはコマンドプロンプトで以下の手順を実行してください。

**① ポートの使用状況を確認する**

```bat
netstat -ano | findstr :8080
```

**② 出力結果から PID を確認する**

```
TCP    0.0.0.0:8080    0.0.0.0:0    LISTENING    12345
```

一番右の数字（例: `12345`）が、8080 を使用しているプロセスの PID です。

**③ そのプロセスを強制終了する**

```bat
taskkill /PID 12345 /F
```

`12345` の部分は ② で確認した PID に置き換えてください。

---

## 2. オブジェクト名の大文字・小文字の不一致

Controller でモデルに渡したオブジェクト名と、テンプレートで参照している名前が一致していない場合に発生します。

**Controller.java**

```java
@RequestMapping("/form")
public String form(Model model, Form form) {
    model.addAttribute("title", "サンプルフォーム");
    return "form/input";
}
// メソッド引数の変数名 "form"（小文字）がそのままモデル名になる
```

**input.html（誤り）**

```html
<form method="get" action="#" th:action="@{/confirm}" th:object="${Form}">
```

**input.html（正しい）**

```html
<form method="get" action="#" th:action="@{/confirm}" th:object="${form}">
```

> `th:object` に指定する名前は、Controller のメソッド引数と完全に一致させる必要があります。  
> `Form`（大文字始まり）ではなく `form`（小文字始まり）を使用してください。

---

## 3. フィールド名の不一致

フォームオブジェクトのフィールド名と、テンプレートで参照しているフィールド名が一致していない場合に発生します。

**Form.java**

```java
public class Form {
    private String name1; // フィールド名は "name1"

    public Form() { }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }
}
```

**input.html（誤り）**

```html
<form method="get" action="#" th:action="@{/confirm}" th:object="${form}">
    <p>名前：<input type="text" name="name1" th:value="*{name}"></p>
</form>
```

**input.html（正しい）**

```html
<form method="get" action="#" th:action="@{/confirm}" th:object="${form}">
    <p>名前：<input type="text" name="name1" th:value="*{name1}"></p>
</form>
```

> `th:value="*{...}"` や `th:errors="*{...}"` には、`Form.java` で定義したフィールド名をそのまま指定します。  
> `name` ではなく `name1` と書いてください。
