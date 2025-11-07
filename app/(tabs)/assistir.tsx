import { ResizeMode, Video } from 'expo-av';
import { router, useLocalSearchParams } from 'expo-router';
import { useEffect, useState } from 'react';
import { ActivityIndicator, StyleSheet, Text, TouchableOpacity, View } from 'react-native';

// ⚠️ CORRIGIDO: Faltava uma barra '/' entre '..' e 'assets'
const LOCAL_VIDEO_PATH = require('../../assets/videos/12567100_2160_3840_24fps.mp4');

export default function Assistir() {
  const { id } = useLocalSearchParams<{ id: string }>();

  const [videoUrl, setVideoUrl] = useState<any>(null);
  const [loading, setLoading] = useState(true);
  const [errorMsg, setErrorMsg] = useState<string | null>(null);

  useEffect(() => {
    try {
      if (LOCAL_VIDEO_PATH) {
        setVideoUrl(LOCAL_VIDEO_PATH);
      } else {
        setErrorMsg('Caminho do vídeo local não encontrado. Verifique se o arquivo existe.');
      }
    } catch (e) {
      console.error('Erro ao carregar vídeo local:', e);
      setErrorMsg('Erro ao carregar vídeo local. Verifique o caminho e o nome do arquivo.');
    } finally {
      setLoading(false);
    }
  }, [id]); 

  if (loading) {
    return (
      <View style={styles.container}>
        <ActivityIndicator size="large" color="#fff" />
      </View>
    );
  }

  if (errorMsg) {
    return (
      <View style={styles.container}>
        <Text style={styles.textError}>{errorMsg}</Text>
        <TouchableOpacity onPress={() => router.push('/casa')}>
          <Text style={styles.link}>VOLTAR</Text>
        </TouchableOpacity>
      </View>
    );
  }

  if (!videoUrl) {
    return (
      <View style={styles.container}>
        <Text style={styles.textError}>Vídeo não carregado. Verifique o console para erros.</Text>
        <TouchableOpacity onPress={() => router.push('/casa')}>
          <Text style={styles.link}>VOLTAR</Text>
        </TouchableOpacity>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Video
        source={videoUrl} 
        useNativeControls
        resizeMode={ResizeMode.CONTAIN}
        shouldPlay
        style={styles.video}
        onError={(e) => console.error('Erro de reprodução do vídeo:', e)}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    justifyContent: 'center',
    alignItems: 'center',
    padding: 16,
  },
  video: {
    width: '100%',
    height: 300,
    backgroundColor: '#000',
  },
  link: {
    marginTop: 20,
    textAlign: 'center',
    color: '#fff',
    textDecorationLine: 'underline',
    fontSize: 20,
  },
  textError: {
    fontSize: 18,
    color: '#fff',
    textAlign: 'center',
    marginHorizontal: 16,
  },
});