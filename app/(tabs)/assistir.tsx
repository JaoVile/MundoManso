import { useLocalSearchParams, router } from 'expo-router';
import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import { Video, ResizeMode } from 'expo-av';

const videos: Record<string, any> = {
  /*'1': require('../../assets/videos/parte 5.mp4'),
  '2': require('../../assets/videos/191159-889246512_medium.mp4'),*/
};


export default function Assistir() {
  const { id } = useLocalSearchParams();

  console.log('ID recebido na tela Assistir:', id);



  const videoSource = videos[id as string];

  if (!videoSource) {
    return (
      <View style={styles.container}>
        <Text style={{ fontSize: 20, color: '#fff', textAlign: 'center' }}>
          Vídeo não encontrado para o id: {id}
        </Text>
        <TouchableOpacity onPress={() => router.push('/casa')}>
                   <Text style={styles.link}>VOLTAR</Text>
                  </TouchableOpacity>
      </View>
    );
  }
  return (
    <View style={styles.container}>
      <Video
        source={videoSource}
        useNativeControls
        resizeMode={ResizeMode.CONTAIN}
        shouldPlay={true}
        style={styles.video}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    justifyContent: 'center',
  },
  video: {
    width: '100%',
    height: 300,
  },
  link: {
    marginTop: 20,
    textAlign: 'center',
    color: '#fff',
    textDecorationLine: 'underline',
    fontSize: 20,
  },
});
