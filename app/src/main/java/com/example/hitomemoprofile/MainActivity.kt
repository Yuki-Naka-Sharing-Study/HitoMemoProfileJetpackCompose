package com.example.hitomemoprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.hitomemoprofile.ui.theme.HitoMemoProfileTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class ProfileItem(val label: String, val value: String, val hasImage: Boolean = false)

val profileItems = listOf(
    ProfileItem("名前", "田中太郎"),
    ProfileItem("フリガナ", "タナカタロウ"),
    ProfileItem("ニックネーム", "たろちゃん"),
    ProfileItem("性別", "未選択"),
    ProfileItem("誕生日", "2000年1月1日"),
    ProfileItem("出身地", "秋田県"),
    ProfileItem("住所", "東京都", true),
    ProfileItem("職業", "エンジニア"),
    ProfileItem("関係", "会社の同僚"),
    ProfileItem("電話番号", "080-6154-8216", true),
    ProfileItem("Email", "taro@example.com", true),
    ProfileItem("X", "@tanakataro", true),
    ProfileItem("Instagram", "@tanakataro", true),
    ProfileItem("Facebook", "@tanakataro", true),
    ProfileItem("カスタム1", "内容"),
    ProfileItem("カスタム2", "内容"),
    ProfileItem("カスタム3", "内容")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HitoMemoProfileTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProfileList(items = profileItems)
                }
            }
        }
    }
}

@Composable
fun ProfileList(items: List<ProfileItem>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(items) { item ->
            ProfileRow(item = item)
        }
    }
}

@Composable
fun ProfileRow(item: ProfileItem) {
    var text by remember { mutableStateOf(item.value) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.label,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = text,
            onValueChange = { text = it },
            placeholder = { Text(text = item.value) },
            modifier = Modifier.weight(2f)
        )
        if (item.hasImage) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground), // 画像リソースIDを適宜変更
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileListPreview() {
    HitoMemoProfileTheme {
        ProfileList(items = profileItems)
    }
}