import { View, TextInput, Button, StyleSheet, Image, ImageBackground, Text, TouchableOpacity, Alert } from 'react-native';
import { useRouter } from 'expo-router';
import AsyncStorage from '@react-native-async-storage/async-storage';
import { useState } from 'react';

export default function CadastroScreen() {
  const router = useRouter();

  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [senha, setSenha] = useState('');

  const cadastrar = async () => {
    if (!nome || !email || !senha) {
      Alert.alert('Erro', 'Preencha todos os campos!');
      return;
    }

    const dadosUsuario = { nome, email, senha };
    await AsyncStorage.setItem('usuario', JSON.stringify(dadosUsuario));
    await AsyncStorage.setItem('usuarioLogado', 'true');

    Alert.alert('Parabéns!', 'Conta criada com sucesso!', [
      {
        text: 'Ok',
        onPress: () => router.replace('/'),
      },
    ]);
  };

  return (
    <ImageBackground
      source={require('../../assets/images/background_login.jpg')}
      style={styles.background}
      resizeMode="cover"
    >
      <View style={styles.container}>
        <Image
          source={require('../../assets/images/Mundo_Manso_Nome.png')}
          style={styles.logo}
          resizeMode="contain"
        />

        <TextInput
          style={styles.input}
          placeholder="Seu Nome"
          placeholderTextColor="#999"
          onChangeText={setNome}
        />
        <TextInput
          style={styles.input}
          placeholder="Seu E-mail"
          keyboardType="email-address"
          placeholderTextColor="#999"
          onChangeText={setEmail}
        />
        <TextInput
          style={styles.input}
          placeholder="Sua Senha"
          secureTextEntry
          placeholderTextColor="#999"
          onChangeText={setSenha}
        />

        <View style={styles.buttonContainer}>
          <Button title="Cadastrar" onPress={cadastrar} color="#665544" />
        </View>

        <TouchableOpacity onPress={() => router.push('/login')}>
          <Text style={styles.link}>Já tenho uma conta</Text>
        </TouchableOpacity>
      </View>
    </ImageBackground>
  );
}

const styles = StyleSheet.create({
  background: { flex: 1 },
  container: { flex: 1, justifyContent: 'center', padding: 20 },
  logo: { width: '100%', height: 190, marginBottom: 30 },
  input: {
    height: 50,
    borderColor: '#665544',
    borderWidth: 2,
    marginBottom: 8,
    borderRadius: 20,
    paddingHorizontal: 12,
    backgroundColor: '#ffffffdd',
  },
  buttonContainer: { marginTop: 10, borderRadius: 20, overflow: 'hidden' },
  link: {
    marginTop: 20,
    textAlign: 'center',
    color: '#ffffffdd',
    textDecorationLine: 'underline',
    fontSize: 16,
  },
});