-- `sample` テーブルを作成
CREATE TABLE sample(
	-- `id` カラム: 主キー、NULL禁止、自動増加
	id INT NOT NULL AUTO_INCREMENT,
	-- `name` カラム: 100文字までの文字列、NULL禁止
	name VARCHAR(100) NOT NULL,
	-- `id` を主キーとして設定
	PRIMARY KEY(id)
);